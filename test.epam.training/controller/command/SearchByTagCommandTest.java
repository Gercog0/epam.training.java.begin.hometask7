package controller.command;

import by.training.homework7.controller.command.impl.SearchByTagCommand;
import by.training.homework7.exception.UserException;
import by.training.homework7.model.entity.Book;
import model.BookLibraryDataTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class SearchByTagCommandTest {
    SearchByTagCommand command;
    BookLibraryDataTest dataTest;

    @BeforeClass
    public void setUp() {
        command = new SearchByTagCommand();
        dataTest = BookLibraryDataTest.createInstance();
    }

    @Test
    public void executeTestValid() {
        try {
            Book book = dataTest.takeBookLibraryTest().getBooks().get(0);
            int id = book.getId();
            Map<String, String> expected = new HashMap<>();
            List<Book> expectedBook = new ArrayList<>();
            expectedBook.add(book);
            expected.put("SUCCESSFUL SEARCH", expectedBook.toString());
            Map<String, String> actual = command.execute("id", Integer.toString(id));
            assertEquals(expected, actual);
        } catch (UserException exp) {
            fail("UserException" + exp);
        }
    }

    @Test
    public void executeTestInvalid() {
        try {
            Book book = dataTest.takeBookLibraryTest().getBooks().get(1);
            int id = book.getId();
            Map<String, String> expected = new HashMap<>();
            List<Book> expectedBook = new ArrayList<>();
            expectedBook.add(book);
            expected.put("SUCCESSFUL SEARCH", expectedBook.toString());
            Map<String, String> actual = command.execute("id", "534tft3d3");
            assertNotEquals(expected, actual);
        } catch (UserException exp) {
            fail("UserException" + exp);
        }
    }

    @Test(expectedExceptions = UserException.class)
    public void executeTestException() throws UserException {
        command.execute("title", "House", "Mouse");
    }

    @AfterClass
    public void tierDown() {
        command = null;
        dataTest = null;
    }
}
