package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.ItemStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<List<LotDto>> findAll(ItemStatus status);

    Optional<LotDto> register(String itemName, String itemDescribe, Object ownerId, String itemType,
                              String itemPrice);

    void unblock(Item item);

    Optional<LotDto> findValidItemById(long id);

    void doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice);

    void complete(LotDto lotDto);

    Optional<List<LotDto>> findItemsByUserId(int userId);

    Optional<LotDto> findItemById(long id);

    void saveEditedItem(Item item);
}
