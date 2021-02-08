package com.epam.jwd.web.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    private final Lock lock = new ReentrantLock();
    private final Condition retrieveCondition = lock.newCondition();
    private final Condition returnCondition = lock.newCondition();

//    private static final Thread thread

    private final Stack<Connection> freeConnections = new Stack<>();
    private final List<Connection> busyConnections = new LinkedList<>();

    public Connection retrieveConnection() throws InterruptedException {
        Connection connection;
        lock.lock();
        try {
            while (freeConnections.size() == 0) {
                retrieveCondition.await();
            }
            connection = freeConnections.pop();
            busyConnections.add(connection);
            returnCondition.signal();
        }
        finally {
            lock.unlock();
        }

        return connection;
    }

    public void returnConnection(Connection connection) throws InterruptedException {
        lock.lock();
        try {
            while (busyConnections.size() == 0) {
                retrieveCondition.await();
            }
            if (connection != null) {
                for (int i = 0; i < busyConnections.size(); i++) {
                    if (connection.equals(busyConnections.get(i))) {
                        freeConnections.push(connection);
                        busyConnections.remove(i);
                        break;
                    }
                }
            }
            retrieveCondition.signal();
        }
        finally {
            lock.unlock();
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

}
