package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.LotDao;
import com.epam.jwd.web.dao.impl.LotDaoImpl;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemFactory;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.LotService;
import com.epam.jwd.web.service.UserService;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
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
    public boolean doBid(long itemId, long endTime, int bidOwnerId, BigDecimal currentPrice) {
        return LOT_DAO.doBid(itemId, endTime, bidOwnerId, currentPrice);

    }

    @Override
    public boolean insertItemIntoLotHistory(Item item) {
        return LOT_DAO.insertItemIntoLotHistory(item);
    }

    @Override
    public boolean complete(LotDto lot) {
        return (ITEM_SERVICE.complete(lot.getItemId()) && USER_SERVICE.complete(lot));
    }

    @Override
    public boolean block(LotDto lot) {
        boolean updateAccountResult = false;
        if (lot.getOwnerId() != lot.getBidOwnerId() && lot.getType().equals(ItemType.STRAIGHT)) {
            updateAccountResult = USER_SERVICE.updateAccount(lot.getBidOwnerId(), lot.getPrice().negate());
        }
        final Optional<Item> optionalItem = ITEM_SERVICE.findItemById(lot.getItemId());

        if (optionalItem.isPresent()) {
            final Item updatedItem = createItemWithBlockedStatus(optionalItem.get());
            return ITEM_SERVICE.update(updatedItem) && updateAccountResult;
        }
        return false;
    }

    private Item createItemWithBlockedStatus(Item item) {
        return ItemFactory.INSTANCE.createItem(item.getId(), item.getName(), item.getDescribe(), item.getOwner(),
                item.getType(), item.getPrice(), ItemStatus.BLOCKED, 0);
    }
}
