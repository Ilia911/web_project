package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.observer.Publisher;

import java.util.List;
import java.util.Optional;

public interface ItemDao  extends Publisher<Long> {

    boolean register(String itemName, String itemDescribe, int ownerId, int itemType, long itemPrice);

    Optional<List<Item>> findItemsByStatus(ItemStatus status);

    Optional<Item> update(Item item);

    Optional<List<Item>> findItemsByUserId(int userId);

    Optional<LotDto> findItemById(long id);

    void complete(long id);
}
