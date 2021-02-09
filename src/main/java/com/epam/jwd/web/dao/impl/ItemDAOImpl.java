package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.dao.ItemDAO;
import com.epam.jwd.web.dao.exeption.ItemDAOException;
import com.epam.jwd.web.entity.Item;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item newItem) throws ItemDAOException {
        return false;
    }

    @Override
    public List<Item> retrieveItemsBy() throws ItemDAOException {
        return null;
    }
}
