package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.service.ItemService;

import java.util.List;
import java.util.Optional;

public enum ItemServiceImpl implements ItemService {
    INSTANCE();

    private static final ItemDao ITEM_DAO = ItemDaoImpl.INSTANCE;

    @Override
    public Optional<List<Item>> findAll(ItemStatus status) {
        return ITEM_DAO.findItemsByStatus(status);
    }

    @Override
    public boolean register(String itemName, String itemDescribe, Object ownerId, String itemType,
                            String itemPrice) {

        return ITEM_DAO.register(itemName, itemDescribe, Integer.parseInt(ownerId.toString()),
                Integer.parseInt(itemType), Integer.parseInt(itemPrice));
    }

    @Override
    public boolean update(Item item) {
       return ITEM_DAO.update(item);
    }

    @Override
    public boolean complete(long itemId) {
        return ITEM_DAO.complete(itemId);
    }

    @Override
    public Optional<List<Item>> findItemsByUserId(int id) {
        return ITEM_DAO.findItemsByUserId(id);
    }

    @Override
    public Optional<Item> findItemById(long id) {
        return ITEM_DAO.findItemById(id);
    }
}
