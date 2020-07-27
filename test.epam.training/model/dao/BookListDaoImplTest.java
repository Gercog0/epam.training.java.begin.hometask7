package model.dao;

import by.training.homework7.exception.DaoException;
import by.training.homework7.model.dao.impl.BookListDaoImpl;
import by.training.homework7.model.entity.Book;
import model.BookLibraryDataTest;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.*;

public class BookListDaoImplTest {
    BookListDaoImpl dao;
    BookLibraryDataTest testData;

    @BeforeClass
    public void setUp() {
        dao = BookListDaoImpl.createInstance();
        testData = BookLibraryDataTest.createInstance();
    }


    @DataProvider(name = "dataSearchValid")
    public Object[][] dataSearchTestValid() {
        return new Object[][]{
                {Book.Tag.ID, testData.takeBookLibraryTest().getBooks().get(0).getId(), testData.searchBookById()},
                {Book.Tag.AUTHORS, "Bgor", testData.searchBookById()},
                {Book.Tag.PAGES, "500", testData.searchBookByPages()},
                {Book.Tag.YEAR, "2000", testData.searchBookByYear()},
                {Book.Tag.TITLE, "WouseWork", testData.searchBookByTitle()},
                {Book.Tag.PRICE, "160", testData.searchBookByPrice()},
        };
    }

    @DataProvider(name = "dataSearchInvalid")
    public Object[][] dataSearchTestInvalid() {
        return new Object[][]{
                {Book.Tag.ID, testData.takeBookLibraryTest().getBooks().get(1).getId(), testData.searchBookById()},
                {Book.Tag.AUTHORS, "Alex", testData.searchBookById()},
                {Book.Tag.PAGES, "110", testData.searchBookByPages()},
                {Book.Tag.YEAR, "2010", testData.searchBookByYear()},
                {Book.Tag.TITLE, "Cle", testData.searchBookByTitle()},
                {Book.Tag.PRICE, "1170", testData.searchBookByPrice()},
        };
    }



    @Test(dataProvider = "dataSearchValid")
    public void searchByTagTestValid(Book.Tag tag, String string, List<Book> expected) {
        try {
            List<Book> actual = dao.searchByTag(tag, string);
            assertEquals(expected, actual);
        } catch (DaoException exp) {
            fail("DaoException");
        }
    }

    @Test(dataProvider = "dataSearchInvalid")
    public void searchByTagTestInvalid(Book.Tag tag, String string, List<Book> expected) {
        try {
            List<Book> actual = dao.searchByTag(tag, string);
            assertNotEquals(expected, actual);
        } catch (DaoException exp) {
            fail("DaoException");
        }
    }

    @Test(expectedExceptions = DaoException.class)
    public void searchByTagTestException() throws DaoException {
        dao.searchByTag(Book.Tag.AUTHORS, "no author");
    }

    @AfterClass
    public void tierDown() {
        dao = null;
        testData = null;
    }
}
