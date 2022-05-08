package command;

import collection.CollectionData;
import correspondency.ResponseCo;
import storage.Worker;

import java.util.ArrayList;
import java.util.Collections;

@PointCommand(name="print_descending", description = "вывести элементы коллекции в порядке убывания")
public class CommandPrintDescending extends Command {

    @Override
    public void execute() {
        ArrayList<Worker> tmp = (ArrayList<Worker>) CollectionData.collection.getCollection().clone();
        Collections.sort(tmp);
        String response = "";
        for (int i = tmp.size() - 1; i >= 0; i--) {
            response += tmp.get(i) + "\n";
        }
        setResponse(new ResponseCo(response));
    }
}
