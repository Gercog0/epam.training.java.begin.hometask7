package by.training.homework7.controller.command.impl;

import by.training.homework7.controller.command.Command;
import by.training.homework7.exception.ServiceException;
import by.training.homework7.exception.UserException;
import by.training.homework7.model.entity.Book;
import by.training.homework7.model.service.BookOperationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllCommand implements Command {
    private static final int QUANTITY_PARAMETERS_TO_FIND_ALL = 0;
    private static final String SUCCESSFUL_REPLY = "SUCCESSFUL SEARCH";
    private static final String UNSUCCESSFUL_REPLY = "UNSUCCESSFUL SEARCH";

    @Override
    public Map<String, String> execute(String... parameters) throws UserException {
        if (parameters == null || QUANTITY_PARAMETERS_TO_FIND_ALL != parameters.length) {
            throw new UserException("Incorrect data...");
        }

        BookOperationService service = BookOperationService.createInstance();
        Map<String, String> reply = new HashMap<>();
        List<Book> foundBooks;
        try {
            foundBooks = service.findAll();
            reply.put(SUCCESSFUL_REPLY, foundBooks.toString());
        } catch (ServiceException exp) {
            reply.put(UNSUCCESSFUL_REPLY, exp.getMessage());
        }
        return reply;
    }
}
