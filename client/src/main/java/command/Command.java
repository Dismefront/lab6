package command;

import datastructures.Pair;
import exceptions.CommandDoesNotExistException;

import java.util.ArrayList;

public class Command {

    protected String action;
    protected ArrayList<String> args;
    private String command;

    /**
     * constructor takes the command and invokes distribution method
     * @throws CommandDoesNotExistException
     */

    public void setCommand(String command) {
        this.command = command;
        try {
            this.distributeCommand();
        } catch (CommandDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public String description() {
        return "Command";
    }

    /**
     * if the command is not blank, then it gets split into its action and arguments
     * @throws CommandDoesNotExistException
     */
    private void distributeCommand() throws CommandDoesNotExistException {
        checkCommandCorrectness(this.command);
        Pair<String, ArrayList<String>> p = getSplit(this.command);
        this.action = p.first();
        this.args = p.second();
    }

    private static boolean isBlank(String name) {
        return name == null || name.isEmpty();
    }

    private static void checkCommandCorrectness(String msg) throws CommandDoesNotExistException {
        if (isBlank(msg))
            throw new CommandDoesNotExistException();
    }

    /**
     * takes a string and returns a pair of command and argument list
     * @param command
     * @return
     */
    private static Pair<String, ArrayList<String>> getSplit(String command) {
        String[] parsed = command.trim().split(" ");
        ArrayList<String> na = new ArrayList<>();
        for (int i = 1; i < parsed.length; i++)
            na.add(parsed[i]);
        return new Pair<>(parsed[0], na);
    }

    public void execute() throws CommandDoesNotExistException {
        throw new CommandDoesNotExistException();
    }

}
