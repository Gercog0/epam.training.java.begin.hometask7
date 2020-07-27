package controller;

import by.training.homework7.controller.Invoker;
import by.training.homework7.exception.UserException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class InvokerTest {
    Invoker invoker;


    @BeforeClass
    public void setUp() {
        invoker = Invoker.createInstance();
    }

    @Test
    public void processRequestTestValid() {
        Map<String, String> expected = new HashMap<>();
        expected.put("SUCCESSFUL ADDITION", "BOOK WAS ADDED");

        String currentCommand = "add_book";
        try {
            Map<String, String> actual = invoker.processRequest(currentCommand,
                    "Maybe to maybe", "Gero Sekond", "756", "1998", "34000");
            assertEquals(expected,actual);
        } catch (UserException exp) {
            fail("UserException..." + exp);
        }
    }

    @Test
    public void processRequestTestInvalid() {
        Map<String, String> expected = new HashMap<>();
        expected.put("DELETING..", "DELETED..");

        String currentCommand = "add_book";
        try {
            Map<String, String> actual = invoker.processRequest(currentCommand,
                    "Maybe to maybe", "Gero Sekond", "756", "1998", "34000");
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
    }
}
