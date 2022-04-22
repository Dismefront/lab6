package command;

import exceptions.CommandDoesNotExistException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Invoker {



    private static final HashMap<String, String> commandMap = new HashMap<>();

    public static void register(String commandName, String className) {
        commandMap.put(commandName, className);
    }

    public static Command getCommand(String commandName) throws CommandDoesNotExistException {
        Command command = null;
        try {
            command = (Command) Class
                    .forName(commandMap.get(commandName))
                    .getConstructor()
                    .newInstance();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (command == null) {
            throw new CommandDoesNotExistException();
        }
        return command;
    }

}
