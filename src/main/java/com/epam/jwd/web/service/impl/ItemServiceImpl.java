package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.service.ItemService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public enum ItemServiceImpl implements ItemService {
    INSTANCE;

    private static final ItemDao ITEM_DAO = ItemDaoImpl.INSTANCE;

    @Override
    public Optional<List<Item>> findAll(ItemStatus status) {
        return ITEM_DAO.findItemsByStatus(status);
//                .map(
//                        items -> items.stream()
//                                .map(this::convertToDtoForList)
//                                .collect(Collectors.toList()));
    }

    @Override
    public Optional<LotDto> register(String itemName, String itemDescribe, Object ownerId, String itemType,
                                     String itemPrice) {
        ITEM_DAO.register(itemName, itemDescribe, Integer.parseInt(ownerId.toString()), Integer.parseInt(itemType),Integer.parseInt(itemPrice));

        return Optional.empty();
    }

    @Override
    public void update(Item item) {
        ITEM_DAO.update(item);
    }

    @Override
    public void complete(long itemId) {
        ITEM_DAO.complete(itemId);
    }

    @Override
    public Optional<List<Item>> findItemsByUserId(int id) {
        return ITEM_DAO.findItemsByUserId(id);
    }

    @Override
    public Optional<Item> findItemById(long id) {
        return Optional.empty();
    }

}
