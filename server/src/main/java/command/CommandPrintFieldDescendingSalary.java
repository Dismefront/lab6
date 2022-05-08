package command;

import collection.CollectionData;
import correspondency.ResponseCo;
import storage.Worker;

import java.util.ArrayList;
import java.util.Collections;

@PointCommand(name="print_field_descending_salary", description = "вывести значения поля " +
        "salary всех элементов в порядке убывания")
public class CommandPrintFieldDescendingSalary extends Command {

    @Override
    public void execute() {
        ArrayList<Worker> tmp = (ArrayList<Worker>) CollectionData.collection.getCollection().clone();
        Collections.sort(tmp);
        String response = "";
        for (int i = tmp.size() - 1; i >= 0; i--) {
            response += tmp.get(i).getSalary() + "\n";
        }
        setResponse(new ResponseCo(response));
    }
}
