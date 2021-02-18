package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemType;

import java.util.List;
import java.util.Optional;

public interface ItemDao {
    Optional<List<Item>> findAll();

    boolean register(String itemName, String itemDescribe, ItemType itemType, long itemPrice,
                     long minBid, long time, String userLogin);

    Optional<Item> findById(int id);

    Optional<Item> update(Item item);

    boolean remove(int id);
}
