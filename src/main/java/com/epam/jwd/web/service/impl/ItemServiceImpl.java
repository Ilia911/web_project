package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemDto;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.resource.ApplicationManager;
import com.epam.jwd.web.service.ItemService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum ItemServiceImpl implements ItemService {
    INSTANCE;

    private static final ItemDao itemDao = new ItemDaoImpl();

    @Override
    public Optional<List<ItemDto>> findAll() {
        return itemDao.findAll()
                .map(
                        items -> items.stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList()));
    }

    @Override
    public Optional<ItemDto> register(String itemName, String itemDescribe, String itemType, String itemPrice,
                                      String minBid, long time, Object ownerId) {
        itemDao.register(itemName, itemDescribe, Integer.parseInt(itemType), Long.parseLong(itemPrice),
                Long.parseLong(minBid), time, (Integer) ownerId);

        return Optional.empty();
    }

    private ItemDto convertToDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getDescribe(), item.getOwner(),
                item.getType(), item.getPrice(), item.getBid(), item.getStatus());
    }
}
