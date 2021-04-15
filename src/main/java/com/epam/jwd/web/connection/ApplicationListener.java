package com.epam.jwd.web.connection;

import com.epam.jwd.web.cash.LotCash;
import com.epam.jwd.web.cash.LotManager;
import com.epam.jwd.web.cash.UserCash;
import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.dao.LotDao;
import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.dao.impl.LotDaoImpl;
import com.epam.jwd.web.dao.impl.UserDaoImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.Locale;

@WebListener
public class ApplicationListener implements ServletContextListener {

    private static final Thread LOT_MANAGER = new LotManager();
    private static final LotDao LOT_DAO = LotDaoImpl.INSTANCE;
    private static final ItemDao ITEM_DAO = ItemDaoImpl.INSTANCE;
    private static final UserDao USER_DAO = UserDaoImpl.INSTANCE;
    private static final LotCash LOT_CASH = LotCash.INSTANCE;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.INSTANCE.init();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
        Locale.setDefault(Locale.US);

        LOT_CASH.init();
        LOT_DAO.subscribe(LOT_CASH);
        ITEM_DAO.subscribe(LOT_CASH);
        USER_DAO.subscribe(UserCash.INSTANCE);

        LOT_MANAGER.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        ConnectionPool.INSTANCE.destroy();
    }
}
