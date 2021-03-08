package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.observer.Publisher;
import com.epam.jwd.web.observer.Subscriber;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemDao extends Publisher<LotDto> {

    boolean register(String itemName, String itemDescribe, int ownerId, int itemType, long itemPrice);

    Optional<List<LotDto>> findAll(ItemStatus status);

    Optional<LotDto> findValidItemById(long id);

    Optional<Item> update(Item item);

    boolean remove(int id);

    void unblock(Item item);

    void doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice);

    void complete(LotDto lotDto);

}
