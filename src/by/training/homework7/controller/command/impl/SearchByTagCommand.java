package by.training.homework7.controller.command.impl;

import by.training.homework7.controller.command.Command;
import by.training.homework7.exception.ServiceException;
import by.training.homework7.exception.UserException;
import by.training.homework7.model.entity.Book;
import by.training.homework7.model.service.BookOperationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchByTagCommand implements Command {
    private static final int QUANTITY_PARAMETERS_TO_SEARCH = 2;
    private static final String SUCCESSFUL_REPLY = "SUCCESSFUL SEARCH";
    private static final String UNSUCCESSFUL_REPLY = "UNSUCCESSFUL SEARCH";

    @Override
    public Map<String, String> execute(String... parameters) throws UserException {
        if (parameters == null || parameters.length != QUANTITY_PARAMETERS_TO_SEARCH) {
            throw new UserException("Incorrect data...");
        }
        BookOperationService service = BookOperationService.createInstance();
        Map<String, String> reply = new HashMap<>();

        String tagName = parameters[0];
        Book.Tag currentTag = Book.Tag.getTagByName(tagName);
        String conditionSearch = parameters[1];

        try {
            List<Book> foundBooks = service.searchByTag(currentTag, conditionSearch);
            reply.put(SUCCESSFUL_REPLY, foundBooks.toString());
        } catch (ServiceException exp) {
            reply.put(exp.getMessage(), UNSUCCESSFUL_REPLY);
        }
        return reply;
    }
}
