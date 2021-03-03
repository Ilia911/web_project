package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemDtoForList;
import com.epam.jwd.web.model.ItemStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemDao {

    boolean register(String itemName, String itemDescribe, int ownerId, int itemType, long itemPrice);

    Optional<List<ItemDtoForList>> findAll(ItemStatus status);

    Optional<ItemDtoForList> findValidItemById(long id);

    Optional<Item> update(Item item);

    boolean remove(int id);

    void unblock(Item item);

    void doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice);

}
