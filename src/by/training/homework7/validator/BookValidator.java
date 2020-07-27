package by.training.homework7.validator;

import by.training.homework7.model.entity.Book;

import java.util.List;

public class BookValidator {
    private static final String SPACE = " ";
    private static final int MIN_LENGTH_TITLE = 1;
    private static final int MAX_LENGTH_TITLE = 30;
    private static final int MIN_QUANTITY_PAGES = 5;
    private static final int MAX_QUANTITY_PAGES = 10000;
    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = 2020;
    private static final int MIN_PRICE = 1;
    private static final int MAX_PRICE = 100000;

    public boolean validateBook(Book book) {
        return validateTitle(book.getTitle()) && validateListAuthors(book.getAuthors()) &&
                validateQuantityPages(book.getQuantityPages()) && validateYear(book.getYear()) &&
                validatePrice(book.getPrice());
    }

    public boolean validateByTag(Book.Tag tag, String data) {
        if (data == null) {
            return false;
        }
        boolean result = true;
        switch (tag) {
            case TITLE:

                if (!validateTitle(data)) {
                    result = false;
                }
                break;
            case PRICE:
                if (!validatePrice(Integer.parseInt(data))) {
                    result = false;
                }
                break;
            case YEAR:
                if (!validateYear(Integer.parseInt(data))) {
                    result = false;
                }
                break;
            case PAGES:
                if (!validateQuantityPages(Integer.parseInt(data))) {
                    result = false;
                }
                break;
            case AUTHORS:
                if (!validateAuthor(data)) {
                    result = false;
                }
                break;
        }
        return result;
    }

    public boolean validateListAuthors(List<String> authors) {
        if (authors == null) {
            return false;
        }
        for (String author : authors) {
            if (!validateAuthor(author)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateAuthor(String author) {
        if (author == null) {
            return false;
        }
        String[] nameSurname = author.split(SPACE);
        for (String part : nameSurname) {
            char[] letters = part.toCharArray();
            for (char letter : letters) {
                if (!(Character.isAlphabetic(letter) || letter == ' ')) {
                    return false;
                }

            }
        }
        return true;
    }

    public boolean validateTitle(String title) {
        if (title == null) {
            return false;
        }
        return title.length() >= MIN_LENGTH_TITLE && title.length() <= MAX_LENGTH_TITLE;
    }

    public boolean validateQuantityPages(int quantityPages) {
        return quantityPages >= MIN_QUANTITY_PAGES && quantityPages <= MAX_QUANTITY_PAGES;
    }

    public boolean validateYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }

    public boolean validatePrice(int price) {
        return price >= MIN_PRICE && price <= MAX_PRICE;
    }
}
