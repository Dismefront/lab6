package main;

import command.Command;
import command.CommandAdd;
import command.Invoker;
import exceptions.CommandDoesNotExistException;

/**
 * @author Dismefront(Erik Romaikin)
 */

public class Main {

    public static void main(String ... args) {

        Command c = new CommandAdd();
        c.setCommand("adadfa nigga");

        /*try {
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress("localhost", 8000));
            Socket s = channel.socket();

            CommandAdd cm = new CommandAdd("f");
            cm.execute();

            byte[] arr = ObjectSerializer.toByteArray(cm.getWorker());

            for (int i = 0; i < arr.length; i++)
                System.out.print(arr[i] + " ");
        } catch (IOException | CommandDoesNotExistException e) {
            System.out.println(e.getMessage());
        }*/

        /*int cnt = 0;
        while (true) {
            try {
                Socket s = new Socket("localhost", 8000);
                PrintWriter pr = new PrintWriter(s.getOutputStream());
                pr.println("HELLO");
                pr.flush();
                break;
            } catch (Exception ex) {
                System.out.println("Could not connect. Retrying...");
                if (cnt >= 3)
                    break;
                cnt++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException iex) {
                    break;
                }
            }
        }*/

    }

}
