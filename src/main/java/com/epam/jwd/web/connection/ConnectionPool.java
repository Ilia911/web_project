package com.epam.jwd.web.connection;

import com.epam.jwd.web.entity.User;
import com.epam.jwd.web.util.reader.PropertyReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;

public enum  ConnectionPool {
    INSTANCE;

    private static final String DATABASE_URL = PropertyReader.INSTANCE.getProperties().getProperty("databaseURL");
    private static final String DATABASE_LOGIN = PropertyReader.INSTANCE.getProperties().getProperty("databaseLogin");
    private static final String DATABASE_PASSWORD = PropertyReader.INSTANCE.getProperties().getProperty("databasePassword");
    private static final int INITIAL_CONNECTIONS_AMOUNT =
            Integer.parseInt(PropertyReader.INSTANCE.getProperties().getProperty("minConnectionsAmount"));
    private static final int MAX_CONNECTIONS_AMOUNT =
            Integer.parseInt(PropertyReader.INSTANCE.getProperties().getProperty("maxConnectionsAmount"));

    private Stack<Connection> connections = new Stack<>();

    public Connection retrieveConnection() {
        return connections.pop();

    }
    public void returnConnection(Connection connection){
        connections.push(connection);
    }
//    public static void main(String[] args) {
//
//        try (final Connection connection = DriverManager
//                .getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
//             final Statement statement = connection.createStatement();
//             final ResultSet resultSet = statement.executeQuery("select * from a_user where id = 1")
//
//        ) {
//            resultSet.next();
//            User user = new User(resultSet.getInt("id"), resultSet.getString("u_login"),
//                    resultSet.getString("u_password"));
//            System.out.println(user.toString());
//        }
//        catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//    }

}
