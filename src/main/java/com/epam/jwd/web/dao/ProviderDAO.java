package com.epam.jwd.web.dao;

import com.epam.jwd.web.dao.impl.ItemDAOImpl;
import com.epam.jwd.web.dao.impl.UserDAOImpl;

public class ProviderDAO {
    private static final ProviderDAO INSTANCE = new ProviderDAO();
    private UserDAO userDAO = new UserDAOImpl();
    private ItemDAO itemDAO = new ItemDAOImpl();

    private ProviderDAO() {}

    public static ProviderDAO getInstance() {
        return INSTANCE;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }
}
