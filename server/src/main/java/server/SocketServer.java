package server;

import collection.WorkerCollection;
import command.*;
import correspondency.CommandType;
import correspondency.RequestCo;
import correspondency.ResponseCo;
import serialization.ObjectSerializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketServer {

    private Selector selector;
    private InetSocketAddress address;
    private SocketChannel session;
    private String workingFile = null;

    public SocketServer(String host, int port) {
        this.address = new InetSocketAddress(host, port);
    }

    public void start() throws IOException {
        this.selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(this.address);
        ssc.configureBlocking(false);
        ssc.register(this.selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started...");

        new Thread(ServerConsole::read).start();

        while (true) {
            this.selector.select();
            for (SelectionKey key : this.selector.selectedKeys()) {
                if (!key.isValid())
                    continue;
                if (key.isAcceptable()) {
                    try {
                        this.accept(key);
                    }
                    catch (IOException ex) {
                        System.out.println("Client cannot be accepted");
                        break;
                    }
                }
                else if (key.isReadable()) {
                    try {
                        this.read(key);
                    }
                    catch (IOException ex) {
                        System.out.println("Client disconnected");
                        this.session.close();
                        break;
                    }
                }
            }
            this.selector.selectedKeys().clear();
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel channel = ssc.accept();
        channel.configureBlocking(false);
        channel.register(this.selector, SelectionKey.OP_READ);
        if (this.session != null)
            this.session.close();
        this.session = channel;
        System.out.println("accepted " + this.session);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        int read = channel.read(buffer);
        if (read == -1) {
            channel.close();
            return;
        }
        byte[] data = new byte[read];
        System.arraycopy(buffer.array(), 0, data, 0, read);
        RequestCo req = ObjectSerializer.fromByteArray(data);

        if (req == null)
            return;

        ResponseCo res = null;
        if (req.hasWorkingFile()) {
            this.workingFile = req.getWorkingFile();
            CommandSave.setPath(this.workingFile);
        }
        if (req.hasArray()) {
            req.getObjArray().forEach(CommandAdd::addWorker);
            res = new ResponseCo("Successfully added "
                    + req.getObjArray().size()
                    + " elements");
        }
        else if (req.hasObject()) {

            if (req.getCommandType().equals(CommandType.ADD)) {
                CommandAdd.addWorker(req.getObj());
                res = new ResponseCo("Successfully added");
            }
            else if (req.getCommandType().equals(CommandType.ADDIFMIN)) {
                res = new ResponseCo(
                        CommandAddIfMin.addWorker(req.getObj())
                );
            }
            else if (req.getCommandType().equals(CommandType.UPDATEID)) {
                res = new ResponseCo(
                        CommandUpdateId.updateId(req.getObj(), req.getId())
                );
            }

        }
        else {
            Command c = Invoker.launch(req);
            if (c != null) {
                res = c.getResponse();
            }
            else {
                res = new ResponseCo("Wrong command");
            }
        }
        sendResponse(key, res);

        System.out.println("Got: " + req.getLine());
    }

    private void sendResponse(SelectionKey key,
                              ResponseCo response) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        channel.configureBlocking(false);

        byte[] arr = ObjectSerializer.toByteArray(response);
        channel.write(ByteBuffer.wrap(arr));
    }

}
