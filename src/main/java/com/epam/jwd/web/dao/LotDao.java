package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.observer.Publisher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LotDao extends Publisher<Long> {

    Optional<List<LotDto>> findAll();

    Optional<LotDto> findLotById(long id);

    void doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice);

    void insertItemIntoLotHistory(Item item);
}
