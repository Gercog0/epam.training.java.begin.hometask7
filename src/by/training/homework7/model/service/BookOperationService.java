package by.training.homework7.model.service;

import by.training.homework7.exception.DaoException;
import by.training.homework7.exception.ServiceException;
import by.training.homework7.model.dao.impl.BookListDaoImpl;
import by.training.homework7.model.entity.Book;
import by.training.homework7.validator.BookValidator;

import java.util.ArrayList;
import java.util.List;

public class BookOperationService {
    private static BookOperationService instance;

    private BookOperationService() {
    }

    public static BookOperationService createInstance() {
        if (instance == null) {
            instance = new BookOperationService();
        }
        return instance;
    }

    public void addBook(String title, ArrayList<String> authors,
                        int quantityPages, int year, int price) throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.validateBook(title, authors, quantityPages, year, price)) {
            throw new ServiceException("Incorrect data...");
        }

        Book newBook = new Book(title, authors, quantityPages, year, price);
        try {
            BookListDaoImpl.createInstance().add(newBook);
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
    }



    public List<Book> searchByTag(Book.Tag tag, String search) throws ServiceException {
        BookValidator validator = new BookValidator();
        if (tag == null || search == null) {
            throw new ServiceException("Incorrect data");
        }
        if (!validator.validateByTag(tag, search)) {
            throw new ServiceException("Error during data validation");
        }
        List<Book> books;
        try {
            books = BookListDaoImpl.createInstance().searchByTag(tag, search);
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
        return books;
    }
}
