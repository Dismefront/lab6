package client;

import storage.Worker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Dismefront(Erik Romaikin)
 */

public class Main {

    public static void main(String ... args) throws IOException {
        ArrayList<Worker> workers = null;
        String workingFile = "default.txt";
        if (args.length != 0) {
            String filename = args[0];
            workers = FileHelper.addFromCsvFile(filename);
            workingFile = args[0];
        }
        Client client = new Client("localhost", 8080);
        if (!(workers == null || workers.isEmpty()))
            client.setObjectsFromFile(workers);
        client.setWorkingFile(workingFile);
        while (true) {
            client.start();
        }
    }

}
