package validator;

import by.training.homework7.model.entity.Book;
import by.training.homework7.validator.BookValidator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.*;

public class BookValidatorTest {
    BookValidator validator;

    @BeforeClass
    public void setUp() {
        validator = new BookValidator();
    }

    @Test
    public void validatePriceTestValid() {
        boolean condition = validator.validatePrice(99999);
        assertTrue(condition);
    }

    @Test
    public void validatePriceTestInvalid() {
        boolean condition = validator.validatePrice(1000000);
        assertFalse(condition);
    }

    @Test
    public void validateYearTestValid() {
        boolean condition = validator.validateYear(2020);
        assertTrue(condition);
    }

    @Test
    public void validateYearTestInvalid() {
        boolean condition = validator.validateYear(2040);
        assertFalse(condition);
    }

    @Test
    public void validateQuantityPagesTestValid() {
        boolean condition = validator.validateQuantityPages(300);
        assertTrue(condition);
    }

    @Test
    public void validateQuantityPagesTestInvalid() {
        boolean condition = validator.validateQuantityPages(-300);
        assertFalse(condition);
    }

    @Test
    public void validateTitleTestValid() {
        boolean condition = validator.validateTitle("Three mouse");
        assertTrue(condition);
    }

    @Test
    public void validateTitleTestInvalid() {
        boolean condition = validator.validateTitle("Three mouse and a lot of mouse in the house");
        assertFalse(condition);
    }

    @Test
    public void validateAuthorTestValid() {
        boolean condition = validator.validateAuthor("Ivan Yanushkevich");
        assertTrue(condition);
    }

    @Test
    public void validateAuthorTestInvalid() {
        boolean condition = validator.validateAuthor("Ivan 9");
        assertFalse(condition);
    }

    @Test
    public void validateListAuthorsTestValid() {
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Stas Karaps");
        authors.add("Maykle");
        authors.add("John Roberton");
        boolean condition = validator.validateListAuthors(authors);
        assertTrue(condition);
    }

    @Test
    public void validateListAuthorsTestInvalid() {
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Stas,");
        authors.add("20");
        authors.add("John Roberton 9");
        boolean condition = validator.validateListAuthors(authors);
        assertFalse(condition);
    }

    @Test
    public void validateByTagTestValid() {
        boolean condition = validator.validateByTag(Book.Tag.AUTHORS, "John Makey");
        assertTrue(condition);
    }

    @Test
    public void validateByTagTestInvalid() {
        boolean condition = validator.validateByTag(Book.Tag.AUTHORS, null);
        assertFalse(condition);
    }

    @Test
    public void validateBookTestValid() {
        boolean condition = validator.validateBook("Mouse",
                new ArrayList<String>(Arrays.asList("Igor Kremov", "Max Belyash")), 700, 2015, 9000);
        assertTrue(condition);
    }

    @Test
    public void validateBookTestInvalid() {
        boolean condition = validator.validateBook("Mouse",
                new ArrayList<String>(Arrays.asList("Igor Kremov", "Max Belyash")), -700, 2015, 9000);
        assertFalse(condition);
    }

    @AfterClass
    public void tierDown() {
        validator = null;
    }
}
