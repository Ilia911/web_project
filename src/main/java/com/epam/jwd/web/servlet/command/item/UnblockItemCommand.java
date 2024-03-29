package com.epam.jwd.web.servlet.command.item;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemFactory;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.LotService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.service.impl.LotServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowBlockedItemsCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

public enum UnblockItemCommand implements Command {
    INSTANCE;

    private static final ItemService ITEM_SERVICE = ItemServiceImpl.INSTANCE;
    private static final LotService LOT_SERVICE = LotServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {

        Item updatedItem = createUnblockedItem(req);

        ITEM_SERVICE.update(updatedItem);
        LOT_SERVICE.insertItemIntoLotHistory(updatedItem);

        return ShowBlockedItemsCommand.INSTANCE.execute(req);
    }

    private Item createUnblockedItem(RequestContent req) {

        final long currentTime = GregorianCalendar.getInstance().getTimeInMillis();
        final String item_show_time = req.getContextParameter("item_show_time");
        final long bid_time = currentTime + Long.parseLong(item_show_time);

        return ItemFactory.INSTANCE.createItem(Long.parseLong(req.getRequestParameter("id")[0]),
                req.getRequestParameter("name")[0],
                req.getRequestParameter("describe")[0],
                Integer.parseInt(req.getRequestParameter("ownerId")[0]),
                ItemType.valueOf(req.getRequestParameter("type")[0]),
                BigDecimal.valueOf(Double.parseDouble(req.getRequestParameter("price")[0])),
                ItemStatus.VALID,
                bid_time);
    }
}
