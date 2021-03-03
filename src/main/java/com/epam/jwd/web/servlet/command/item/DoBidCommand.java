package com.epam.jwd.web.servlet.command.item;

import com.epam.jwd.web.model.ItemDtoForList;
import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowAllLotsCommand;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public enum DoBidCommand implements Command {
    INSTANCE;

    private final static ItemService ITEM_SERVICE = ItemServiceImpl.INSTANCE;
    private final static UserService USER_SERVICE = UserServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {
        // actualize data:
        final Optional<ItemDtoForList> optionalItem
                = ITEM_SERVICE.findValidItemById(Long.parseLong(req.getRequestParameter("itemId")[0]));
        if (!optionalItem.isPresent()) {
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        ItemDtoForList item = optionalItem.get();
        if (item.getTime() > Long.parseLong(req.getRequestParameter("previousTime")[0])) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle
                    ("message.do.bid.not.actual", (Locale) req.getSessionAttribute("locale")));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }

        // check sufficiency of available sum of money:

        final Optional<UserDto> newBidOwner
                = USER_SERVICE.findById(Integer.parseInt(req.getRequestParameter("newBidOwnerId")[0]));
        if (!newBidOwner.isPresent()) {
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        final BigDecimal newPrice
                = item.getPrice().add(BigDecimal.valueOf(Long.parseLong(req.getRequestParameter("bid")[0])));
        final BigDecimal availableMoney = newBidOwner.get().getAccount();
        final BigDecimal allowableSum
                = BigDecimal.valueOf(Long.parseLong(req.getContextParameter("allowable_debt"))).add(availableMoney);
        if (newPrice.subtract(allowableSum).doubleValue() > 0) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle
                    ("message.do.bid.not.enough.money", (Locale) req.getSessionAttribute("locale")));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        final BigDecimal newUserAccount = availableMoney.subtract(newPrice);

        // do bid and return money previous bidder
        final long possibleEndTime = GregorianCalendar.getInstance().getTimeInMillis()
                + Long.parseLong(req.getContextParameter("item_additional_show_time"));
        final long endTime = Math.max(item.getTime(), possibleEndTime);
        ITEM_SERVICE.doBid(item.getId(), endTime, newBidOwner.get().getId(), newPrice);
        USER_SERVICE.updateAccount(newBidOwner.get().getId(), newUserAccount);

        //todo: add previous bid to previous bidder account

        return ShowAllLotsCommand.INSTANCE.execute(req);
    }
}
