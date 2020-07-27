package controller.command;

import by.training.homework7.controller.command.impl.AddBookCommand;
import by.training.homework7.exception.UserException;
import model.BookLibraryDataTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class AddBookCommandTest {
    AddBookCommand addCommand;
    BookLibraryDataTest dataTest;

    @BeforeClass
    public void setUp() {
        addCommand = new AddBookCommand();
        dataTest = BookLibraryDataTest.createInstance();
    }

    @Test
    public void executeTestValid() {
        try {
            Map<String, String> expected = new HashMap<>();
            expected.put("SUCCESSFUL ADDITION", "BOOK WAS ADDED");
            Map<String, String> actual = addCommand.execute("Madam", "John", "100", "2000", "10000");
            assertEquals(expected, actual);
        } catch (UserException exp) {
            fail("UserException" + exp);
        }
    }

    @Test
    public void executeTestInvalid() {
        try {
            Map<String, String> expected = new HashMap<>();
            expected.put("ERROR", "UNSUCCESSFUL ADDITION");
            Map<String, String> actual = addCommand.execute("Madam", "John", "100", "2000", "10000");
            assertNotEquals(expected, actual);
        } catch (UserException exp) {
            fail("UserException" + exp);
        }
    }

    @Test(expectedExceptions = UserException.class)
    public void executeTestException() throws UserException {
        addCommand.execute(null);
    }

    @AfterClass
    public void tierDown() {
        addCommand = null;
        dataTest = null;
    }
}
