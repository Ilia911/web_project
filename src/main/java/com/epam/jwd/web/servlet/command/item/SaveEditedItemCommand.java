package com.epam.jwd.web.servlet.command.item;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemFactory;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.LotService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.service.impl.LotServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowUserItemsCommand;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Optional;

public enum SaveEditedItemCommand implements Command {
    INSTANCE;

    private static final ItemService ITEM_SERVICE = ItemServiceImpl.INSTANCE;
    private static final LotService LOT_SERVICE = LotServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {

        final Optional<LotDto> optionalLotDto
                = LOT_SERVICE.findLotByItemId(Long.parseLong(req.getRequestParameter("id")[0]));

        optionalLotDto.ifPresent(LOT_SERVICE::block);

        ITEM_SERVICE.update(createUpdatedItem(req));

        return ShowUserItemsCommand.INSTANCE.execute(req);
    }

    private Item createUpdatedItem(RequestContent req) {
        return ItemFactory.INSTANCE.createItem(
                Integer.parseInt(req.getRequestParameter("id")[0]),
                req.getRequestParameter("name")[0],
                req.getRequestParameter("describe")[0],
                (Integer) req.getSessionAttribute("id"),
                ItemType.of(req.getRequestParameter("type")[0]),
                BigDecimal.valueOf(Double.parseDouble(req.getRequestParameter("price")[0])),
                ItemStatus.BLOCKED,
                GregorianCalendar.getInstance().getTimeInMillis()
        );
    }
}
