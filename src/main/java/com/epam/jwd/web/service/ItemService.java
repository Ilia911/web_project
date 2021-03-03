package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemDtoForList;
import com.epam.jwd.web.model.ItemStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<List<ItemDtoForList>> findAll(ItemStatus status);

    Optional<ItemDtoForList> register(String itemName, String itemDescribe, Object ownerId, String itemType,
                                      String itemPrice);

    void unblock(Item item);

    Optional<ItemDtoForList> findValidItemById(long id);

    void doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice);


}
