package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.UserStatus;
import com.epam.jwd.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {


    private static final String TABLE_NAME = "auction_user";
    private static final String ID_COLUMN_NAME = "id";
    private static final String LOGIN_COLUMN_NAME = "user_login";
    private static final String PASSWORD_COLUMN_NAME = "user_password";
    private static final String NAME_COLUMN_NAME = "user_name";
    private static final String ACCOUNT_COLUMN_NAME = "user_account";
    private static final String ROLE_COLUMN_NAME = "user_role";
    private static final String STATUS_COLUMN_NAME = "user_status";

    private static final String FIND_ALL_USERS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + LOGIN_COLUMN_NAME + ", "
            + PASSWORD_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", " + ACCOUNT_COLUMN_NAME + ", "
            + ROLE_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME;
    protected static final String FIND_USER_BY_LOGIN_SQL = "SELECT " + ID_COLUMN_NAME + ", " + LOGIN_COLUMN_NAME + ", "
            + PASSWORD_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", " + ACCOUNT_COLUMN_NAME + ", "
            + ROLE_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME
            + " WHERE " + LOGIN_COLUMN_NAME + " = ?";
    private static final String REGISTER_USER_SQL = "INSERT INTO " + TABLE_NAME + " ( " + LOGIN_COLUMN_NAME + ", "
            + PASSWORD_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ")  VALUES (?, ?, ?, ?)";


    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);


    public Optional<User> findByLogin(String login) {

        try (Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_SQL);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = readUser(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    @Override
    public boolean removeByLogin(String login) {
        return false;
    }

    public Optional<User> register(String userLogin, String userPassword, String userName) {

        try (Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_USER_SQL);
            preparedStatement.setString(1, userLogin);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setString(3, userName);

            preparedStatement.executeUpdate();

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return findByLogin(userLogin);
    }
    private User readUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(ID_COLUMN_NAME),
                resultSet.getString(LOGIN_COLUMN_NAME),
                resultSet.getString(PASSWORD_COLUMN_NAME),
                resultSet.getString(NAME_COLUMN_NAME),
                resultSet.getBigDecimal(ACCOUNT_COLUMN_NAME),
                Role.of(resultSet.getString(ROLE_COLUMN_NAME)),
                UserStatus.of(resultSet.getString(STATUS_COLUMN_NAME))
        );
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User entity) {
        return Optional.empty();
    }

}
