package controller.command;

import by.training.homework7.controller.command.impl.DeleteBookCommand;
import by.training.homework7.exception.UserException;
import model.BookLibraryDataTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class DeleteBookCommandTest {
    DeleteBookCommand delete;
    BookLibraryDataTest dataTest;

    @BeforeClass
    public void setUp() {
        delete = new DeleteBookCommand();
        dataTest = BookLibraryDataTest.createInstance();
    }

    @Test
    public void executeTestValid() {
        try {
            int idToDelete = dataTest.takeBookLibraryTest().getBooks().get(0).getId();
            Map<String, String> expected = new HashMap<>();
            expected.put("SUCCESSFUL DELETING", "BOOK WAS DELETED");
            Map<String, String> actual = delete.execute(Integer.toString(idToDelete));
            assertEquals(expected, actual);
        } catch (UserException exp) {
            fail("UserException" + exp);
        }
    }

    @Test
    public void executeTestInvalid() {
        try {
            int idToDelete = dataTest.takeBookLibraryTest().getBooks().get(0).getId();
            Map<String, String> expected = new HashMap<>();
            expected.put("ERROR", "UNSUCCESSFUL DELETING");
            Map<String, String> actual = delete.execute(Integer.toString(idToDelete));
            assertNotEquals(expected, actual);
        } catch (UserException exp) {
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
        dataTest = null;
    }
}
