package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.UserDAO;
import com.epam.jwd.web.dao.exeption.UserDAOException;
import com.epam.jwd.web.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOImpl implements UserDAO {
    private final ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    @Override
    public boolean authorization(String login, String password) throws UserDAOException {
        String sql = "SELECT * FROM a_user where u_login = ? and u_password = ?";

        try (Connection connection = connectionPool.retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String sqlLogin = resultSet.getString("u_login");
            if (sqlLogin != null && sqlLogin.equals(login)) {
                return true;
            }

        } catch (InterruptedException e) {
            throw new UserDAOException("Connection Lock was interrupted!");
        } catch (SQLException e) {
            throw new UserDAOException(e);
        }
        return false;
    }

    @Override
    public boolean registration(User newUser) throws UserDAOException {
        return false;
    }
}
