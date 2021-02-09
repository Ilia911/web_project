package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.UserDAO;
import com.epam.jwd.web.dao.exeption.UserDAOException;
import com.epam.jwd.web.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private final ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    @Override
    public boolean authorization(String login, String password) throws UserDAOException {
        String sql = "select * from auction_user where user_login = ?";
        boolean result = false;

        try (Connection connection = connectionPool.retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (password != null && password.equals(resultSet.getString("user_name"))) {
                return true;
            }
        } catch (InterruptedException e) {
            throw new UserDAOException("Connection Lock was interrupted!");
        } catch (SQLException e) {
            throw new UserDAOException(e);
        }

        return result;
    }

    @Override
    public boolean registration(User newUser) throws UserDAOException {
        return false;
    }
}
