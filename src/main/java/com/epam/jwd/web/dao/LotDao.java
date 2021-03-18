package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.observer.Publisher;
import com.epam.jwd.web.observer.Subscriber;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LotDao extends Publisher<Long> {

    Optional<List<LotDto>> findAll();

    Optional<LotDto> findLotByItemId(long id);

    boolean doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice);

    boolean insertItemIntoLotHistory(Item item);
}
