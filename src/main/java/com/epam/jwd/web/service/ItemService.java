package com.epam.jwd.web.service;

import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.entity.Item;
import com.epam.jwd.web.entity.ItemDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum  ItemService implements CommonService<ItemDto> {
    INSTANCE;

    private static final CommonDao<Item> itemDao = new ItemDaoImpl();

    @Override
    public Optional<List<ItemDto>> findAll() {
        return itemDao.findAll()
                .map(
                        items -> items.stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList()));


    }

    @Override
    public Optional<ItemDto> save(ItemDto dto) {
        return Optional.empty();
    }

    private ItemDto convertToDto(Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getDescribe(), item.getOwner(),
                item.getType(), item.getPrice(), item.getBid(), item.getStatus());
    }
}
