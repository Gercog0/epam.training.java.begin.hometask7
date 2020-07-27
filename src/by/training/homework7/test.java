package by.training.homework7;

import by.training.homework7.exception.DaoException;
import by.training.homework7.model.dao.impl.BookListDaoImpl;
import by.training.homework7.model.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) throws DaoException {
        BookListDaoImpl impl = BookListDaoImpl.createInstance();
        System.out.println(impl.searchByTag(Book.Tag.PAGES, "1007"));


    }
}
