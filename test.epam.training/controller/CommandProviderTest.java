package controller;

import by.training.homework7.controller.CommandProvider;
import by.training.homework7.controller.command.Command;
import by.training.homework7.controller.command.CommandType;
import by.training.homework7.exception.UserException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommandProviderTest {
    CommandProvider provider;

    @BeforeClass
    public void setUp() {
        provider = new CommandProvider();
    }

    @Test
    public void provideCommandTestValid() {
        try {
            Command expected = CommandType.ADD_BOOK.getCommand();
            Command actual = provider.provideCommand("add_book");
            assertEquals(expected, actual);
        } catch (UserException exp) {
            fail("UserException..." + exp);
        }
    }

    @Test
    public void provideCommandTestInvalid() {
        try {
            Command expected = CommandType.ADD_BOOK.getCommand();
            Command actual = provider.provideCommand("delete_book");
            assertNotEquals(expected, actual);
        } catch (UserException exp) {
            fail("UserException..." + exp);
        }
    }

    @Test(expectedExceptions = UserException.class)
    public void provideCommandTestException() throws UserException {
        provider.provideCommand(null);
    }

    @AfterClass
    public void tierDown() {
        provider = null;
    }
}
