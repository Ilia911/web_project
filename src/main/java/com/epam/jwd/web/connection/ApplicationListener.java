package com.epam.jwd.web.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class ApplicationListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.INSTANCE.init();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
        LOGGER.info("Connection pool was successfully initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        try {
            ConnectionPool.INSTANCE.destroy();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("Connection pool wasn't successfully destroyed");
        }
        LOGGER.info("Connection pool was successfully destroyed");
    }
}
