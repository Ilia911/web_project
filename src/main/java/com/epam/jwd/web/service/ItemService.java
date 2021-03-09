package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.ItemStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<List<Item>> findAll(ItemStatus status);

    Optional<LotDto> register(String itemName, String itemDescribe, Object ownerId, String itemType,
                              String itemPrice);

    void update(Item item);


    void complete(long itemId);

    Optional<List<Item>> findItemsByUserId(int userId);

    Optional<Item> findItemById(long id);

}
