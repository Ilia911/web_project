package com.epam.jwd.web.connection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Connection pool.
 * It can automatically enlarge or decries quantity of connections.
 *
 * @author Ilia Eriomkin
 */
public enum ConnectionPool {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

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

    private final Thread thread = new AddConnectionThread();

    private final Stack<ProxyConnection> freeConnections = new Stack<ProxyConnection>();
    private final List<ProxyConnection> busyConnections = new LinkedList<>();
    private Connection connection;
    private int currentAmountOfConnections = 0;

    /**
     * Initializing connection pool.
     * @throws SQLException
     */
    public void init() throws SQLException {
        registerDrivers();
        for (int i = 0; i < INITIAL_CONNECTIONS_AMOUNT; i++) {
            addConnection();
        }
        thread.setDaemon(true);
        thread.start();
        LOGGER.info("Connection pool was successfully initialized");
    }

    /**
     * <li>Retrieve connection from this.</li>
     * <li>Check sufficiency of current amount of free connections and if needed enlarge connection pool.</li>
     *
     * @return connection from connection pool.
     * @throws InterruptedException
     */
    public Connection retrieveConnection() throws InterruptedException {

        lock.lock();
        try {
            while (freeConnections.size() == 0) {
                retrieveCondition.await();
            }
            connection = freeConnections.pop();
            busyConnections.add((ProxyConnection) connection);
            enlargeCondition.signal();
        } finally {
            lock.unlock();
        }
        return connection;
    }

    /**
     * If returned connection is valid and current amount of free connections more than enough, then close connection.
     *
     * @param returnedConnection is returned connection.
     */
    public void returnConnection(Connection returnedConnection) {
        if (returnedConnection == null) {
            return;
        }
        lock.lock();
        try {
            for (ProxyConnection busyConnection : busyConnections) {
                if (returnedConnection.equals(busyConnection)) {
                    busyConnections.remove(busyConnection);
                    if (checkRedundancyOfCurrentAmountOfFreeConnections()) {
                        ((ProxyConnection) returnedConnection).closeConnection();
                        --currentAmountOfConnections;
                    } else {
                        freeConnections.push((ProxyConnection) returnedConnection);
                        retrieveCondition.signal();
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private boolean checkRedundancyOfCurrentAmountOfFreeConnections() {
        final double currentPercentOfFreeConnections = (double) freeConnections.size() / currentAmountOfConnections;
        if (currentPercentOfFreeConnections > 0.25 && currentAmountOfConnections > INITIAL_CONNECTIONS_AMOUNT) {
            return true;
        }
        return false;
    }

    private void addConnection() throws SQLException {
        final Connection realConnection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
        final ProxyConnection proxyConnection = new ProxyConnection(realConnection);
        freeConnections.push(proxyConnection);
        ++currentAmountOfConnections;
    }

    public void destroy() {
        for (ProxyConnection freeConnection : freeConnections) {
            freeConnection.closeConnection();
        }
        deregisterDrivers();
        LOGGER.info("Connection pool was successfully destroyed");
    }

    private class AddConnectionThread extends Thread {
        @Override
        public void run() {
            while (true) {
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
