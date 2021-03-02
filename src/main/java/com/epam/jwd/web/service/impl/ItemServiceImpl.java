package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemDtoForList;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.service.ItemService;

import java.util.List;
import java.util.Optional;

public enum ItemServiceImpl implements ItemService {
    INSTANCE;

    private static final ItemDao itemDao = new ItemDaoImpl();

    @Override
    public Optional<List<ItemDtoForList>> findAll(ItemStatus status) {
        return itemDao.findAll(status);
//                .map(
//                        items -> items.stream()
//                                .map(this::convertToDtoForList)
//                                .collect(Collectors.toList()));
    }

    @Override
    public Optional<ItemDtoForList> register(String itemName, String itemDescribe, Object ownerId, String itemType,
                                             String itemPrice) {
        itemDao.register(itemName, itemDescribe, Integer.parseInt(ownerId.toString()), Integer.parseInt(itemType),Integer.parseInt(itemPrice));

        return Optional.empty();
    }

//    private ItemDtoForList convertToDtoForList(Item item) {
//        return new ItemDtoForList(item.getId(), item.getName(), describe, ownerId, item.getType(), item.getPrice(), item.getTime(), bidOwnerId);
//    }
}
