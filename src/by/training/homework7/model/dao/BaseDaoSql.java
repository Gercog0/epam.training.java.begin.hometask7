package by.training.homework7.model.dao;

import by.training.homework7.exception.DaoException;
import by.training.homework7.model.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BaseDaoSql<T extends Entity> {
    boolean add(T t) throws DaoException;

    boolean delete(T t) throws DaoException;

    List<T> findAll() throws DaoException;

    default void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException exp) {
                System.out.println(exp.getMessage());
            }
        }
    }

    default void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException exp) {
                System.out.println(exp.getMessage());
            }
        }
    }
}
