package by.training.homework7.controller.command.impl;

import by.training.homework7.controller.command.Command;

import java.util.HashMap;
import java.util.Map;

public class EmptyCommand implements Command {
    private static final String COMMAND_MESSAGE = "Empty command...";
    private static final String UNSUCCESSFUL_REPLY = "Unsuccessful reply... ";

    @Override
    public Map<String, String> execute(String... parameters) {
        Map<String, String> reply = new HashMap<>();
        reply.put(COMMAND_MESSAGE, UNSUCCESSFUL_REPLY);
        return reply;
    }
}
