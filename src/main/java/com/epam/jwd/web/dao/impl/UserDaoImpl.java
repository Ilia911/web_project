package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.entity.Item;
import com.epam.jwd.web.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements CommonDao<User> {

    private final ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    private static final String TABLE_NAME = "item";
    private static final String ID_COLUMN_NAME = "id";
    private static final String NAME_COLUMN_NAME = "item_name";
    private static final String DESCRIBE_COLUMN_NAME = "item_describe";
    private static final String ID_OWNER_COLUMN_NAME = "owner_id";
    private static final String TYPE_COLUMN_NAME = "item_type";
    private static final String PRICE_COLUMN_NAME = "start_price";
    private static final String BID_COLUMN_NAME = "item_bid";
    private static final String STATUS_COLUMN_NAME = "item_status";
    private static final String FIND_ALL_ITEMS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", "
            + DESCRIBE_COLUMN_NAME + ", " + ID_OWNER_COLUMN_NAME + ", " + TYPE_COLUMN_NAME + ", "
            + PRICE_COLUMN_NAME + ", " + BID_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME;






//    public boolean authorization(String login, String password) {
//        String sql = "SELECT * FROM auction_user where user_login = ? and user_password = ?";
//
//        try (Connection connection = connectionPool.retrieveConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, login);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//
//            String sqlLogin = resultSet.getString("user_login");
//            if (sqlLogin != null && sqlLogin.equals(login)) {
//                return true;
//            }
//
//        } catch (SQLException | InterruptedException throwables) {
//            throwables.printStackTrace();
//        }
//        return false;
//    }

    public boolean registration(User newUser)   {
        return false;
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<User> save(User entity) {
        return Optional.empty();
    }
}
