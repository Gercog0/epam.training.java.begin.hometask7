package controller.command;

import by.training.homework7.controller.command.impl.DeleteBookCommand;
import by.training.homework7.exception.ServiceException;
import by.training.homework7.exception.UserException;
import by.training.homework7.model.entity.Book;
import by.training.homework7.model.service.BookOperationService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class DeleteBookCommandTest {
    DeleteBookCommand delete;


    @BeforeClass
    public void setUp() {
        delete = new DeleteBookCommand();
    }

    @Test
    public void executeTestValid() {
        try {
            BookOperationService service = BookOperationService.createInstance();
            service.addBook(new Book("Maybe", new ArrayList<>(Arrays.asList("Gero Sekond")),
                    756, 1998, 34000));
            int idToDelete = 41;
            Map<String, String> expected = new HashMap<>();
            expected.put("SUCCESSFUL DELETING", "BOOK WAS DELETED");
            Map<String, String> actual = delete.execute(Integer.toString(idToDelete));
            assertEquals(expected, actual);
        } catch (UserException | ServiceException exp) {
            fail("UserException" + exp);
        }
    }

    @Test
    public void executeTestInvalid() {
        try {
            BookOperationService service = BookOperationService.createInstance();
            service.addBook(new Book("Maybe", new ArrayList<>(Arrays.asList("Gero Sekond")),
                    756, 1998, 34000));
            int idToDelete = 36;
            Map<String, String> expected = new HashMap<>();
            expected.put("ERROR", "UNSUCCESSFUL DELETING");
            Map<String, String> actual = delete.execute(Integer.toString(idToDelete));
            assertNotEquals(expected, actual);
        } catch (UserException | ServiceException exp) {
            fail("UserException" + exp);
        }
    }

    @Test(expectedExceptions = UserException.class)
    public void executeTestException() throws UserException {
        delete.execute("gverwbvwe", "erve");
    }

    @AfterClass
    public void tierDown() {
        delete = null;
    }
}