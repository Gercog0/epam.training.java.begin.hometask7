package by.training.homework7.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookLibrary {
    private List<Book> books;

    private static BookLibrary instance;
    private static final int MAX_CAPACITY = 100;

    private BookLibrary() {
        this.books = new ArrayList<>();
    }

    public static BookLibrary createInstance() {
        if (instance == null) {
            instance = new BookLibrary();
        }
        return instance;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public int getSize() {
        return this.books.size();
    }

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public boolean addBook(Book book) {
        if (book == null || books.contains(book)) {
            return false;
        }

        if (getSize() >= MAX_CAPACITY) {
            return false;
        }

        return this.books.add(book);
    }

    public boolean deleteBook(int id) {
        return books.removeIf(book -> book.getId() == id);
    }
}
