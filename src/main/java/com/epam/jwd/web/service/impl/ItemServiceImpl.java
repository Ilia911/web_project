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
    public Optional<List<LotDto>> findAll(ItemStatus status) {
        return ITEM_DAO.findAll(status);
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
    public void unblock(Item item) {
        ITEM_DAO.unblock(item);
    }

    @Override
    public Optional<LotDto> findValidItemById(long id) {
        return ITEM_DAO.findValidItemById(id);
    }

    @Override
    public void doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice) {
        ITEM_DAO.doBid(itemId, bidTime, bidOwnerId, currentPrice);
    }

    @Override
    public void complete(LotDto lotDto) {
        ITEM_DAO.complete(lotDto);
    }

//    private ItemDtoForList convertToDtoForList(Item item) {
//        return new ItemDtoForList(item.getId(), item.getName(), describe, ownerId, item.getType(), item.getPrice(), item.getTime(), bidOwnerId);
//    }
}
