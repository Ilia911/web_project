package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface LotService {

    Optional<List<LotDto>> findAll();

    Optional<LotDto> findLotByItemId(long id);

    boolean doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice);

    boolean insertItemIntoLotHistory(Item item);

    boolean complete(LotDto lot);

    boolean block(LotDto lot);
}
