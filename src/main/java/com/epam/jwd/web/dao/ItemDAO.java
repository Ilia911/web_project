package com.epam.jwd.web.dao;

import com.epam.jwd.web.dao.exeption.ItemDAOException;
import com.epam.jwd.web.entity.Item;

import java.util.List;

public interface ItemDAO {
    boolean add(Item newItem) throws ItemDAOException;

    List<Item> retrieveItemsBy() throws ItemDAOException;
}
