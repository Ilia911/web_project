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

public enum UserDaoImpl implements UserDao {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final String FIND_USER_BY_ID_SQL = "SELECT * FROM auction_user where id = ?";

    private static final String UPDATE_USER_ACCOUNT_SQL = "UPDATE auction_user SET user_account = ? WHERE (id = ?)";

    private static final String FIND_ALL_USERS_SQL = "SELECT * FROM auction_user";

    private static final String UPDATE_USER_SQL = "UPDATE auction_user SET user_name = ?, user_password = ? WHERE (id = ?)";

    private static final String CHANGE_STATUS_SQL = "UPDATE auction_user SET user_status = ? WHERE (id = ?)";

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
    public Optional<User> save(int id, String newName, String newPassword) {

        try (Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Profile was successfully updated!");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return findById(id);
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

    @Override
    public void changeStatus(int id, UserStatus status) {

        try (Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STATUS_SQL);
            preparedStatement.setInt(1, status.getInt());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Status was successfully updated!");
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

    @Override
    public Optional<List<User>> findAll() {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_SQL)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(readUser(resultSet));
            }
            LOGGER.info("Users were successfully read from database");
            return Optional.of(users);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
            LOGGER.error("Reading users in database failed!");
        }
        return Optional.empty();
    }

    private User readUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getBigDecimal(5),
                Role.of(resultSet.getString(6)),
                UserStatus.of(resultSet.getString(7))
        );
    }

    @Override
    public Optional<User> update(User entity) {
        return Optional.empty();
    }

}
