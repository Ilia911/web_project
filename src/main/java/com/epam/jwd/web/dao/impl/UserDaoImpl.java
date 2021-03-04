package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.UserStatus;
import com.epam.jwd.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final String FIND_USER_BY_ID_SQL = "SELECT * FROM auction_user where id = ?";

    private static final String UPDATE_USER_ACCOUNT_SQL = "UPDATE auction_user SET user_account = ? WHERE (id = ?)";

    public Optional<User> findByLogin(String login) {

        try (Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.FIND_USER_BY_LOGIN_SQL);
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

    @Override
    public Optional<User> findById(int id) {
        try (Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL);
            preparedStatement.setInt(1, id);
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
    public void updateAccount(int id, BigDecimal newPrice) {

        final Optional<User> optionalUser = this.findById(id);
        if (!optionalUser.isPresent()) {
            return;
        }
        BigDecimal oldAccount = optionalUser.get().getAccount();
        BigDecimal newAccount = oldAccount.subtract(newPrice);

        try (Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ACCOUNT_SQL);
            preparedStatement.setBigDecimal(1, newAccount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Account was successfully updated!");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
    }

    public Optional<User> register(String userLogin, String userPassword, String userName) {

        try (Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UserSQL.REGISTER_USER_SQL);
            preparedStatement.setString(1, userLogin);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setString(3, userName);
            preparedStatement.executeUpdate();
            LOGGER.info("User was successfully registered!");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return findByLogin(userLogin);
    }

    private User readUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(UserSQL.ID_COLUMN_NAME),
                resultSet.getString(UserSQL.LOGIN_COLUMN_NAME),
                resultSet.getString(UserSQL.PASSWORD_COLUMN_NAME),
                resultSet.getString(UserSQL.NAME_COLUMN_NAME),
                resultSet.getBigDecimal(UserSQL.ACCOUNT_COLUMN_NAME),
                Role.of(resultSet.getString(UserSQL.ROLE_COLUMN_NAME)),
                UserStatus.of(resultSet.getString(UserSQL.STATUS_COLUMN_NAME))
        );
    }

    @Override
    public Optional<List<User>> findAll() {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(UserSQL.FIND_ALL_USERS_SQL)) {
            List<User> items = new ArrayList<>();
            while (resultSet.next()) {
                items.add(readUser(resultSet));
            }
            return Optional.of(items);
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error("InterruptedException in Connection Pool!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("SQLException in Connection Pool!");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User entity) {
        return Optional.empty();
    }

}
