package by.training.homework7.controller;

import by.training.homework7.controller.command.Command;
import by.training.homework7.exception.UserException;

import java.util.Map;

public class Invoker {
    private static Invoker instance;

    private Invoker() {
    }

    public static Invoker createInstance() {
        if (instance == null) {
            instance = new Invoker();
        }
        return instance;
    }

    public Map processRequest(String currentCommand, String... parameters)
            throws UserException {
        CommandProvider provider = new CommandProvider();
        Command command = provider.provideCommand(currentCommand);
        return command.execute(parameters);
    }
}
