package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.dao.LotDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.dao.impl.LotDaoImpl;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.LotService;
import com.epam.jwd.web.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public enum LotServiceImpl implements LotService {
    INSTANCE;

    private static final LotDao LOT_DAO = LotDaoImpl.INSTANCE;
    private static final ItemService ITEM_SERVICE = ItemServiceImpl.INSTANCE;
    private static final UserService USER_SERVICE = UserServiceImpl.INSTANCE;

    @Override
    public Optional<List<LotDto>> findAll() {
        return LOT_DAO.findAll();
    }

    @Override
    public Optional<LotDto> findLotByItemId(long id) {
        return LOT_DAO.findLotByItemId(id);
    }

    @Override
    public void doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice) {
        LOT_DAO.doBid(itemId, bidTime, bidOwnerId, currentPrice);

    }

    @Override
    public void insertItemIntoLotHistory(Item item) {
        LOT_DAO.insertItemIntoLotHistory(item);
    }

    @Override
    public void complete(LotDto lot) {
        ITEM_SERVICE.complete(lot.getItemId());
        USER_SERVICE.complete(lot);

    }
}
