package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemDtoForList;
import com.epam.jwd.web.model.ItemStatus;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    boolean register(String itemName, String itemDescribe, int itemType, long itemPrice,
                     long minBid, long time, int ownerId);

    Optional<List<ItemDtoForList>> findAll(ItemStatus status);

    Optional<Item> findById(int id);

    Optional<Item> update(Item item);

    boolean remove(int id);
}
