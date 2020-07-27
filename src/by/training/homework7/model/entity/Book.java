package by.training.homework7.model.entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book extends Entity {
    private int id;
    private String title;
    private List<String> authors;
    private int quantityPages;
    private int year;
    private int price;

    public enum Tag {
        ID("id"), TITLE("title"), AUTHORS("author"),
        PAGES("pages"), YEAR("year"), PRICE("price");

        private final String name;

        Tag(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        // Converting string to enum for working with command
        private static final Map<String, Tag> LOOKUP_MAP = new HashMap<>();

        static {
            for (Tag element : values()) {
                LOOKUP_MAP.put(element.getName(), element);
            }
        }

        public static Tag getTagByName(String name) {
            return LOOKUP_MAP.get(name);
        }
    }

    public Book(String title, ArrayList<String> authors,
                int quantityPages, int year, int price) {
        this.title = title;
        this.authors = authors;
        this.quantityPages = quantityPages;
        this.year = year;
        this.price = price;
    }

    public Book(int id, String title, ArrayList<String> authors,
                int quantityPages, int year, int price) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.quantityPages = quantityPages;
        this.year = year;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public int getQuantityPages() {
        return quantityPages;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;

        if (quantityPages != book.quantityPages || year != book.year ||
                price != book.price || id != book.id ||
                (title != null ? !title.equals(book.title) : book.title != null)) {
            return false;
        }
        return authors != null ? authors.equals(book.authors) : book.authors == null;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + quantityPages;
        result = 31 * result + year;
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Book:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Title: ").append(title).append("\n");
        sb.append("Authors: ").append(authors).append("\n");
        sb.append("QuantityPages: ").append(quantityPages).append("\n");
        sb.append("Year: ").append(year).append("\n");
        sb.append("Price: ").append(price).append("\n");
        return sb.toString();
    }
}
