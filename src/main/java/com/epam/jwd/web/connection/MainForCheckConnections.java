package com.epam.jwd.web.connection;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.connection.ProxyConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainForCheckConnections {


    private static final Logger LOGGER = LoggerFactory.getLogger(com.epam.jwd.web.connection.ConnectionPool.class);

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
    private final Condition enlargeCondition = lock.newCondition();

    private final Stack<ProxyConnection> freeConnections = new Stack<ProxyConnection>();
    private Connection connection;
    private int currentAmountOfConnections = 0;

    public static void main(String[] args) throws SQLException {
        System.out.println("app started at " + LocalDateTime.now());
        final Connection realConnection2 = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
        final ProxyConnection proxyConnection2 = new ProxyConnection(realConnection2);
        System.out.println("first connection created at " + LocalDateTime.now());
        final Connection realConnection1 = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
        final ProxyConnection proxyConnection1 = new ProxyConnection(realConnection1);
        System.out.println("second connection created at " + LocalDateTime.now());
        System.out.println("Is this two connections equal? - " + proxyConnection2.equals(proxyConnection1));
        proxyConnection1.closeConnection();
        System.out.println("first connection closed at " + LocalDateTime.now());
        proxyConnection2.closeConnection();
        System.out.println("second connection closed at" + LocalDateTime.now());
    }

    protected void init() throws SQLException {
        registerDrivers();
        for (int i = 0; i < INITIAL_CONNECTIONS_AMOUNT; i++) {
            addConnection();
        }

    }

    public Connection retrieveConnection() throws InterruptedException {

        lock.lock();
        try {
            while (freeConnections.size() == 0) {
                retrieveCondition.await();
            }
            connection = freeConnections.pop();
            enlargeCondition.signal();
        } finally {
            lock.unlock();
        }
        return connection;
    }

    public void returnConnection(Connection returnedConnection) {
        lock.lock();
        try {
            if (returnedConnection != null && (returnedConnection.equals(connection))) {
                if (((double) freeConnections.size() / currentAmountOfConnections) > 0.25
                        && currentAmountOfConnections > INITIAL_CONNECTIONS_AMOUNT) {
                    ((ProxyConnection) returnedConnection).closeConnection();
                    --currentAmountOfConnections;
                }
                freeConnections.push((ProxyConnection) returnedConnection);
                retrieveCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    private void addConnection() throws SQLException {
        final Connection realConnection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
        final ProxyConnection proxyConnection = new ProxyConnection(realConnection);
        freeConnections.push(proxyConnection);
        ++currentAmountOfConnections;
    }

    public void destroy() throws SQLException {
        for (ProxyConnection freeConnection : freeConnections) {
            freeConnection.closeConnection();
        }
        deregisterDrivers();
    }

    private class AddConnectionThread extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                while (checkSufficiencyOfCurrentAmountOfFreeConnections()) {
                    enlargeCondition.await();
                }
                addConnection();
            } catch (InterruptedException e) {
                LOGGER.error("Lock was interrupted!");
            } catch (SQLException e) {
                LOGGER.error("It's not allowed to create additional connection!");
            } finally {
                lock.unlock();
            }
        }

        private boolean checkSufficiencyOfCurrentAmountOfFreeConnections() {
            final double currentPercentOfFreeConnections = (double) freeConnections.size() / currentAmountOfConnections;
            if (currentPercentOfFreeConnections < 0.25 && currentAmountOfConnections < MAX_CONNECTIONS_AMOUNT) {
                return false;
            }
            return true;
        }
    }

    private static void registerDrivers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(DriverManager.getDriver(DATABASE_URL));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



