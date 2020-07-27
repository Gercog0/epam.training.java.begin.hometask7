package by.training.homework7.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnectionCreator {
    private static final String SQL_URL = "jdbc:mysql://localhost:3306/book_library";
    private static final String SQL_USERNAME = "root";
    private static final String SQL_PASSWORD = "gercog";

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(SQL_URL, SQL_USERNAME, SQL_PASSWORD);
    }
}
