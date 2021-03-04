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

        return chain(req);
    }

    private ResponseContext chain(RequestContent req) {
        return checkLogIn(req);
    }

    private ResponseContext checkLogIn(RequestContent req) {
        if (req.getSessionAttribute("login") == null) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.not.logged.in"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        return checkValidityOfItem(req);
    }

    private ResponseContext checkValidityOfItem(RequestContent req) {

        final Optional<ItemDtoForList> optionalItem
                = ITEM_SERVICE.findValidItemById(Long.parseLong(req.getRequestParameter("itemId")[0]));
        if (!optionalItem.isPresent()) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.not.actualize.data"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        return checkDataActuality(req, optionalItem.get());
    }

    private ResponseContext checkDataActuality(RequestContent req, ItemDtoForList item) {
        if (item.getPrice().compareTo(BigDecimal.valueOf(Double.parseDouble(req.getRequestParameter
                ("previousPrice")[0]))) > 0) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.not.actual"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }

        return checkValidityOfNewBidder(req, item);
    }

    private ResponseContext checkValidityOfNewBidder(RequestContent req, ItemDtoForList item) {

        final Optional<UserDto> newBidOwner = USER_SERVICE.findById((Integer) req.getSessionAttribute("id"));
        if (!newBidOwner.isPresent()) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.not.actualize.data"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        return checkSumOfMoneyOnBidderAccount(req, item, newBidOwner.get());
    }

    private ResponseContext checkSumOfMoneyOnBidderAccount(RequestContent req, ItemDtoForList item, UserDto bidder) {
        final BigDecimal newPrice
                = item.getPrice().add(BigDecimal.valueOf(Long.parseLong(req.getRequestParameter("bid")[0])));
        final BigDecimal availableMoney = bidder.getAccount();
        final BigDecimal allowableSum
                = BigDecimal.valueOf(Long.parseLong(req.getContextParameter("allowable_debt"))).add(availableMoney);
        if (allowableSum.compareTo(newPrice) < 0) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.not.enough.money"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        return doBid(req, item, bidder, newPrice);
    }

    private ResponseContext doBid(RequestContent req, ItemDtoForList item, UserDto bidder, BigDecimal newPrice) {

        final long possibleEndTime = GregorianCalendar.getInstance().getTimeInMillis()
                + Long.parseLong(req.getContextParameter("item_additional_show_time"));
        final long endTime = Math.max(item.getTime(), possibleEndTime);
        ITEM_SERVICE.doBid(item.getId(), endTime, bidder.getId(), newPrice);
        USER_SERVICE.updateAccount(bidder.getId(), newPrice);
        USER_SERVICE.updateAccount(Integer.parseInt(req.getRequestParameter
                ("previousBidOwnerId")[0]), item.getPrice().negate());
        return ShowAllLotsCommand.INSTANCE.execute(req);
    }

}