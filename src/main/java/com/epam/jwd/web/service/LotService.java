package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LotService {


    Optional<List<LotDto>> findAll();

    Optional<LotDto> findLotByItemId(long id);

    void doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice);

    void insertItemIntoLotHistory(Item item);

    void complete(LotDto lot);
}
