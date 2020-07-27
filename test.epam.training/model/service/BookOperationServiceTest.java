package model.service;

import by.training.homework7.exception.ServiceException;
import by.training.homework7.model.entity.Book;
import by.training.homework7.model.service.BookOperationService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;
import static org.testng.Assert.fail;

public class BookOperationServiceTest {
    BookOperationService service;

    @BeforeClass
    public void setUp() {
        service = BookOperationService.createInstance();
    }

    @Test
    public void addTestValid() {
        Book newBook = new Book("Maybe to maybe", new ArrayList<>(Arrays.asList("Gero Sekond")),
                756, 1998, 34000);
        try {
            boolean condition = service.addBook(newBook);
            assertTrue(condition);
        } catch (ServiceException exp) {
            fail("ServiceException...");
        }
    }

    @Test
    public void deleteTestValid() {
        Book newBook = new Book("Maybe to maybe", new ArrayList<>(Arrays.asList("Gero Sekond")),
                756, 1998, 34000);
        try {
            boolean condition = service.deleteBook(newBook);
            assertTrue(condition);
        } catch (ServiceException exp) {
            fail("ServiceException...");
        }
    }

    @Test
    public void searchByTagTestValid() {
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(4, "Java", new ArrayList<>(Arrays.asList("Paul Maskov")),
                1007, 2020, 90550));
        expected.add(new Book(16, "Java", new ArrayList<>(Arrays.asList("Torn Folk")),
                1407, 2018, 90140));
        try {
            List<Book> actual = service.searchByTag(Book.Tag.TITLE, "Java");
            assertEquals(expected, actual);
        } catch (ServiceException exp) {
            fail("ServiceException");
        }
    }

    @Test
    public void searchByTagTestInvalid() {
        List<Book> expected = new ArrayList<>();
        expected.add(new Book(5, "Java", new ArrayList<>(Arrays.asList("Paul Maskov")),
                1317, 2015, 550));
        expected.add(new Book(3, "Java", new ArrayList<>(Arrays.asList("Torn Folk")),
                1457, 2019, 9140));
        try {
            List<Book> actual = service.searchByTag(Book.Tag.TITLE, "Java");
            assertNotEquals(expected, actual);
        } catch (ServiceException exp) {
            fail("ServiceException");
        }
    }

    @Test(expectedExceptions = ServiceException.class)
    public void searchByTagTestException() throws ServiceException {
        service.searchByTag(Book.Tag.AUTHORS, null);
    }


    @Test(expectedExceptions = ServiceException.class)
    public void addBookTestException() throws ServiceException {
        Book newBook = new Book("Hello", new ArrayList<>(), 0, 0, 90);
        service.addBook(newBook);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void deleteBookTestException() throws ServiceException {
        service.deleteBook(null);
    }

    @AfterClass
    public void tierDown() {
        service = null;
    }
}
