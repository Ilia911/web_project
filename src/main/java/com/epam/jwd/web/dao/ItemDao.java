package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {
    Optional<List<Item>> findAll();

    boolean register(String itemName, String itemDescribe, int itemType, long itemPrice,
                     long minBid, long time, int ownerId);

    Optional<Item> findById(int id);

    Optional<Item> update(Item item);

    boolean remove(int id);
}
