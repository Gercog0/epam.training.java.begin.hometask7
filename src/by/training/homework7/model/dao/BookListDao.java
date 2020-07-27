package by.training.homework7.model.dao;

import by.training.homework7.exception.DaoException;
import by.training.homework7.model.entity.Book;

import java.util.List;

public interface BookListDao extends BaseDaoSql<Book> {
    List<Book> searchByTag(Book.Tag tag, String string) throws DaoException;
}
