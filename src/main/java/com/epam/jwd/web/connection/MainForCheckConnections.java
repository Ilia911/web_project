package com.epam.jwd.web.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class MainForCheckConnections {
    private static final ResourceBundle RESOURCE = ResourceBundle.getBundle("database");
    private static final String DATABASE_URL = RESOURCE.getString("databaseURL");
    private static final String DATABASE_LOGIN = RESOURCE.getString("databaseLogin");
    private static final String DATABASE_PASSWORD = RESOURCE.getString("databasePassword");

    public static void main(String[] args) throws SQLException {
        System.out.println("app started at " + LocalDateTime.now());
        registerDrivers();
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
        deregisterDrivers();
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



