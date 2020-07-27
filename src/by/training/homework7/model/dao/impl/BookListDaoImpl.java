package by.training.homework7.model.dao.impl;

import by.training.homework7.exception.DaoException;
import by.training.homework7.model.connection.SqlConnectionCreator;
import by.training.homework7.model.dao.BookListDao;
import by.training.homework7.model.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookListDaoImpl implements BookListDao {
    private static BookListDaoImpl instance;

    private static final String SQL_ADD_BOOK = "INSERT INTO books(title, author, pages, year, price) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_BOOK = "DELETE FROM books WHERE title = ? AND " +
            "author = ? AND pages = ? AND year = ? AND price = ?";
    private static final String SQL_FIND_ALL = "SELECT id, title, author, pages, year, price " +
            "FROM books";
    private static final String SQL_SEARCH_BY_TAG = SQL_FIND_ALL + " WHERE ";

    private BookListDaoImpl() {
    }

    public static BookListDaoImpl createInstance() {
        if (instance == null) {
            instance = new BookListDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean add(Book book) throws DaoException {
        boolean update;
        try (Connection connection = SqlConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_BOOK)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthors().get(0));
            statement.setInt(3, book.getQuantityPages());
            statement.setInt(4, book.getYear());
            statement.setInt(5, book.getPrice());
            update = statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException("The book is not attached...", exp);
        }
        return update;
    }

    @Override
    public boolean delete(Book book) throws DaoException {
        boolean update;
        try (Connection connection = SqlConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BOOK)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthors().get(0));
            statement.setInt(3, book.getQuantityPages());
            statement.setInt(4, book.getYear());
            statement.setInt(5, book.getPrice());
            update = statement.executeUpdate() > 0;
        } catch (SQLException exp) {
            throw new DaoException("The book was not deleted...", exp);
        }
        return update;
    }

    @Override
    public List<Book> searchByTag(Book.Tag tag, String string) throws DaoException {
        List<Book> foundBooks = new ArrayList<>();
        try (Connection connection = SqlConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SEARCH_BY_TAG +
                     tag.getName() + " = " + " '" + string + "'");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String author = resultSet.getString(3);
                int pages = resultSet.getInt(4);
                int year = resultSet.getInt(5);
                int price = resultSet.getInt(6);
                foundBooks.add(new Book(id, title, new ArrayList<String>(Arrays.asList(author)),
                        pages, year, price));
            }

        } catch (SQLException exp) {
            throw new DaoException("Error when searching for books...", exp);
        }
        return foundBooks;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> foundBooks = new ArrayList<>();
        try (Connection connection = SqlConnectionCreator.createConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String author = resultSet.getString(3);
                int pages = resultSet.getInt(4);
                int year = resultSet.getInt(5);
                int price = resultSet.getInt(6);
                foundBooks.add(new Book(id, title, new ArrayList<String>(Arrays.asList(author)),
                        pages, year, price));
            }
        } catch (SQLException exp) {
            throw new DaoException("Error when searching for books...", exp);
        }
        return foundBooks;
    }
}
