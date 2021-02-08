package com.epam.jwd.web.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public enum  ConnectionPool {
    INSTANCE;

    private static final ResourceBundle RESOURCE = ResourceBundle.getBundle("database");

    private static final String DATABASE_URL = RESOURCE.getString("databaseURL");
    private static final String DATABASE_LOGIN = RESOURCE.getString("databaseLogin");
    private static final String DATABASE_PASSWORD = RESOURCE.getString("databasePassword");

    private static final int INITIAL_CONNECTIONS_AMOUNT =
            Integer.parseInt(RESOURCE.getString("minConnectionsAmount"));
    private static final int MAX_CONNECTIONS_AMOUNT =
            Integer.parseInt(RESOURCE.getString("maxConnectionsAmount"));

    private final Stack<Connection> freeConnections = new Stack<>();
    private final List<Connection> busyConnections = new ArrayList<>();

    public Connection retrieveConnection() {
        Connection connection = freeConnections.pop();
        busyConnections.add(connection);
        return connection;
    }

    public void returnConnection(Connection connection){
        if (connection != null) {
            for (int i = 0; i < busyConnections.size(); i++) {
                if (connection.equals(busyConnections.get(i))) {
                    freeConnections.push(connection);
                    busyConnections.remove(i);
                    break;
                }
            }
        }
    }

    private void init() throws SQLException {
        for (int i = 0; i <= INITIAL_CONNECTIONS_AMOUNT; i++) {
            addConnection();
        }
    }

    private boolean checkSufficiencyOfCurrentAmountOfFreConnections() {
        final int currentAmountOfConnections = freeConnections.size() + busyConnections.size();
        final double currentPercentOfFreeConnections = freeConnections.size()/currentAmountOfConnections;
        if (currentPercentOfFreeConnections < 0.25 && currentAmountOfConnections < MAX_CONNECTIONS_AMOUNT) {
            return false;
        }
        return true;
    }

    private void addConnection() throws SQLException {
        final Connection realConnection= DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
        final ProxyConnection proxyConnection = new ProxyConnection(realConnection);
        freeConnections.push(proxyConnection);
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

   static class EnlargeConnectionPoolHelper {


    }

}
