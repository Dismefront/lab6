package command;

import collection.CollectionData;
import correspondency.ResponseCo;
import storage.Worker;

@PointCommand(name="show", description = "вывести в стандартный поток вывода все " +
        "элементы коллекции в строковом представлении")
public class CommandShow extends Command {

    @Override
    public void execute() {
        String response = "";
        if (CollectionData.collection.isEmpty())
            response = "The collection is empty";
        for (Worker it : CollectionData.collection)
            response += it + "\n";
        setResponse(new ResponseCo(response));
    }
}
