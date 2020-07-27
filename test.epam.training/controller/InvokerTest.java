package controller;

import by.training.homework7.controller.Invoker;
import by.training.homework7.exception.UserException;
import model.BookLibraryDataTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class InvokerTest {
    Invoker invoker;
    BookLibraryDataTest dataTest;

    @BeforeClass
    public void setUp() {
        invoker = Invoker.createInstance();
        dataTest = BookLibraryDataTest.createInstance();
    }

    @Test
    public void processRequestTestValid() {
        Map<String, String> expected = new HashMap<>();
        expected.put("SUCCESSFUL DELETING", "BOOK WAS DELETED");

        int idToDelete = dataTest.takeBookLibraryTest().getBooks().get(0).getId();
        String currentCommand = "delete_book";
        try {
            Map<String, String> actual = invoker.processRequest(currentCommand, Integer.toString(idToDelete));
            assertEquals(expected,actual);
        } catch (UserException exp) {
            fail("UserException..." + exp);
        }
    }

    @Test
    public void processRequestTestInvalid() {
        Map<String, String> expected = new HashMap<>();
        expected.put("DELETING..", "DELETED..");

        int idToDelete = dataTest.takeBookLibraryTest().getBooks().get(0).getId();
        String currentCommand = "delete_book";
        try {
            Map<String, String> actual = invoker.processRequest(currentCommand, Integer.toString(idToDelete));
            assertNotEquals(expected,actual);
        } catch (UserException exp) {
            fail("UserException..." + exp);
        }
    }

    @Test(expectedExceptions = UserException.class)
    public void processRequestTestException() throws UserException {
        invoker.processRequest("delete_book", "id", "geg5");
    }

    @AfterClass
    public void tierDown() {
        invoker = null;
        dataTest = null;
    }
}
