package by.training.homework7.controller.command.impl;

import by.training.homework7.controller.command.Command;
import by.training.homework7.exception.ServiceException;
import by.training.homework7.exception.UserException;
import by.training.homework7.model.service.BookOperationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddBookCommand implements Command {
    private static final int QUANTITY_PARAMETERS_TO_ADD = 5;
    private static final String SUCCESSFUL_REPLY = "SUCCESSFUL ADDITION";
    private static final String RESULT_REPLY = "BOOK WAS ADDED";
    private static final String UNSUCCESSFUL_REPLY = "UNSUCCESSFUL ADDITION";

    @Override
    public Map<String, String> execute(String... parameters) throws UserException {
        if (parameters == null || parameters.length < QUANTITY_PARAMETERS_TO_ADD) {
            throw new UserException("Incorrect data...");
        }
        BookOperationService service = BookOperationService.createInstance();
        Map<String, String> reply = new HashMap<>();

        String title = parameters[0];
        /*
        The number of authors of the book is unlimited, so you need to find
        the next parameter using the private method. Since author names cannot
        contain digits, the method searches for the first occurrence of a digit
        in an array element and returns the index of this element.
         */
        int indexPages = selectIndexAuthorsInArray(parameters);
        if (indexPages + 2 >= parameters.length) {
            throw new UserException("Incorrect data...");
        }

        ArrayList<String> authors = new ArrayList<>();
        for (int i = 1; i < indexPages; i++) {
            authors.add(parameters[i]);
        }
        int pages = Integer.parseInt(parameters[indexPages]);
        int year = Integer.parseInt(parameters[indexPages + 1]);
        int price = Integer.parseInt(parameters[indexPages + 2]);

        try {
            service.addBook(title, authors, pages, year, price);
            reply.put(SUCCESSFUL_REPLY, RESULT_REPLY);
        } catch (ServiceException exp) {
            reply.put(exp.getMessage(), UNSUCCESSFUL_REPLY);
        }
        return reply;
    }

    private int selectIndexAuthorsInArray(String... parameters) throws UserException {
        for (int i = 1; i < parameters.length; i++) {
            char[] symbols = parameters[i].toCharArray();
            for (int j = 0; j < symbols.length; j++) {
                if (!Character.isAlphabetic(symbols[j])) {
                    return i;
                }
            }
        }
        throw new UserException("Incorrect data...");
    }
}
