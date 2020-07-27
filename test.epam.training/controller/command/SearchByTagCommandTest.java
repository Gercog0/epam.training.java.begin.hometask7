package controller.command;

import by.training.homework7.controller.command.impl.SearchByTagCommand;
import by.training.homework7.exception.UserException;
import by.training.homework7.model.entity.Book;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

public class SearchByTagCommandTest {
    SearchByTagCommand command;


    @BeforeClass
    public void setUp() {
        command = new SearchByTagCommand();

    }

    @Test
    public void executeTestValid() {
        try {
            Book newBook = new Book(34,"Maybe to maybe", new ArrayList<>(Arrays.asList("Gero Sekond")),
                    756, 1998, 34000);
            String title = newBook.getTitle();
            Map<String, String> expected = new HashMap<>();
            List<Book> expectedBook = new ArrayList<>();
            expectedBook.add(newBook);
            expected.put("SUCCESSFUL SEARCH", expectedBook.toString());
            Map<String, String> actual = command.execute("title", title);
            assertEquals(expected, actual);
        } catch (UserException exp) {
            fail("UserException" + exp);
        }
    }

    @Test
    public void executeTestInvalid() {
        try {
            Book newBook = new Book("Maybe to maybe (fake)", new ArrayList<>(Arrays.asList("No name")),
                    756, 1998, 4000);
            int id = newBook.getId();
            Map<String, String> expected = new HashMap<>();
            List<Book> expectedBook = new ArrayList<>();
            expectedBook.add(newBook);
            expected.put("SUCCESSFUL SEARCH", expectedBook.toString());
            Map<String, String> actual = command.execute("id", "56");
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

    }
}
