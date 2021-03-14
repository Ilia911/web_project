package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemStatus;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<List<Item>> findAll(ItemStatus status);

    boolean register(String itemName, String itemDescribe, Object ownerId, String itemType,
                              String itemPrice);

    void update(Item item);

    void complete(long itemId);

    Optional<List<Item>> findItemsByUserId(int userId);

    Optional<Item> findItemById(long id);

}
