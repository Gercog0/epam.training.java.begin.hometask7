package by.training.homework7.controller.command;

import by.training.homework7.exception.UserException;

import java.util.Map;

public interface Command {
    Map<String, String> execute(String... parameters) throws UserException;
}
