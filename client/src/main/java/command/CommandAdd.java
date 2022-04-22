package command;

import exceptions.CommandDoesNotExistException;
import storage.Worker;

public class CommandAdd extends Command {

    static {
        try {
            Invoker.register("add", CommandAdd.class.getName());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Worker w;

    public Worker getWorker() {
        return w;
    }

    @Override
    public void execute() {
        try {
            w = InputParser.getWorkerFromInput();
            System.out.println(w);
            //Vars.curCol.add(w);
            System.out.println("Successfully added");
        }
        catch (Exception ex) {
            System.exit(0);
        }
    }

}
