package by.training.homework7.model.service;

import by.training.homework7.exception.DaoException;
import by.training.homework7.exception.ServiceException;
import by.training.homework7.model.dao.impl.BookListDaoImpl;
import by.training.homework7.model.entity.Book;
import by.training.homework7.validator.BookValidator;

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

    public boolean addBook(Book book) throws ServiceException {
        BookValidator validator = new BookValidator();
        if (!validator.validateBook(book)) {
            throw new ServiceException("Incorrect data...");
        }
        boolean isAdd;
        try {
            isAdd = BookListDaoImpl.createInstance().add(book);
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
        return isAdd;
    }

    public boolean deleteBook(Book book) throws ServiceException {
        if (book == null) {
            throw new ServiceException("Incorrect data...");
        }
        boolean isDelete;
        try {
            isDelete = BookListDaoImpl.createInstance().delete(book);
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
        return isDelete;
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

    public List<Book> findAll() throws ServiceException {
        List<Book> books;
        try {
            books = BookListDaoImpl.createInstance().findAll();
        } catch (DaoException exp) {
            throw new ServiceException(exp);
        }
        return books;
    }
}
