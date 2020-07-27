package model.dao;

import by.training.homework7.exception.DaoException;
import by.training.homework7.model.dao.impl.BookListDaoImpl;
import by.training.homework7.model.entity.Book;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class BookListDaoImplTest {
    BookListDaoImpl dao;

    @BeforeClass
    public void setUp() {
        dao = BookListDaoImpl.createInstance();
    }

    @Test
    public void addTestValid() {
        Book newBook = new Book("Maybe to maybe", new ArrayList<>(Arrays.asList("Gero Sekond")),
                756, 1998, 34000);
        try {
            boolean condition = dao.add(newBook);
            assertTrue(condition);
        } catch (DaoException exp) {
            fail("DaoException...");
        }
    }

    @Test
    public void deleteTestValid() {
        Book newBook = new Book("Maybe to maybe", new ArrayList<>(Arrays.asList("Gero Sekond")),
                756, 1998, 34000);
        try {
            boolean condition = dao.delete(newBook);
            assertTrue(condition);
        } catch (DaoException exp) {
            fail("DaoException...");
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
            List<Book> actual = dao.searchByTag(Book.Tag.TITLE, "Java");
            assertEquals(expected, actual);
        } catch (DaoException exp) {
            fail("DaoException");
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
            List<Book> actual = dao.searchByTag(Book.Tag.TITLE, "Java");
            assertNotEquals(expected, actual);
        } catch (DaoException exp) {
            fail("DaoException");
        }
    }

    @AfterClass
    public void tierDown() {
        dao = null;
    }
}
