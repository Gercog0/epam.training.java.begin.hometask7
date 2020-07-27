package by.training.homework7.controller.command.impl;

import by.training.homework7.controller.command.Command;
import by.training.homework7.exception.ServiceException;
import by.training.homework7.exception.UserException;
import by.training.homework7.model.service.BookOperationService;

import java.util.HashMap;
import java.util.Map;

public class DeleteBookCommand implements Command {
    private static final int QUANTITY_PARAMETERS_TO_DELETE = 1;
    private static final String SUCCESSFUL_REPLY = "SUCCESSFUL DELETING";
    private static final String RESULT_REPLY = "BOOK WAS DELETED";
    private static final String UNSUCCESSFUL_REPLY = "UNSUCCESSFUL DELETING";

    @Override
    public Map<String, String> execute(String... parameters) throws UserException {
        if (parameters == null || parameters.length != QUANTITY_PARAMETERS_TO_DELETE) {
            throw new UserException("Incorrect data...");
        }
        BookOperationService service = BookOperationService.createInstance();
        Map<String, String> reply = new HashMap<>();

        String bookId = parameters[0];

      // try {
      //     service.deleteBook(bookId);
      //     reply.put(SUCCESSFUL_REPLY, RESULT_REPLY);
      // } catch (ServiceException exp) {
      //     reply.put(exp.getMessage(), UNSUCCESSFUL_REPLY);
      // }
        return reply;
    }
}
