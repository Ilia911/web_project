package com.epam.jwd.web.connection;

import com.epam.jwd.web.cash.LotCash;
import com.epam.jwd.web.cash.LotManager;
import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.dao.LotDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.dao.impl.LotDaoImpl;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.observer.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@WebListener
public class ApplicationListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);
    private static final Thread lotManager = new LotManager();
    private static final LotDao LOT_DAO = LotDaoImpl.INSTANCE;
    private static final ItemDao ITEM_DAO = ItemDaoImpl.INSTANCE;
    private static final Subscriber<Long> subscriber = LotCash.INSTANCE;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.INSTANCE.init();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
        Locale.setDefault(Locale.US);
        lotManager.start();

        LOT_DAO.subscribe(subscriber);
        ITEM_DAO.subscribe(subscriber);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        ConnectionPool.INSTANCE.destroy();

        LOGGER.info("Connection pool was successfully destroyed");
    }
}
