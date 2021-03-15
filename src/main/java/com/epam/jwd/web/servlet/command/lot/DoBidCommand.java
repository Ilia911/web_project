package com.epam.jwd.web.servlet.command.lot;

import com.epam.jwd.web.cash.LotCash;
import com.epam.jwd.web.cash.UserCash;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.UserStatus;
import com.epam.jwd.web.service.LotService;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.LotServiceImpl;
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

    private final static LotService LOT_SERVICE = LotServiceImpl.INSTANCE;
    private final static UserService USER_SERVICE = UserServiceImpl.INSTANCE;
    private final static UserCash USER_CASH = UserCash.INSTANCE;
    private final static LotCash LOT_CASH = LotCash.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {
        USER_CASH.actualizeUserData(req);
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
        return checkOwner(req);
    }

    private ResponseContext checkOwner(RequestContent req) {
        if (req.getSessionAttribute("id").equals(Integer.parseInt(req.getRequestParameter("ownerId")[0]))) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.your.lot"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        return checkValidityOfItem(req);
    }

    private ResponseContext checkValidityOfItem(RequestContent req) {

        final Optional<LotDto> optionalItem
                = LOT_CASH.getLot(Long.parseLong(req.getRequestParameter("itemId")[0]));
        if (!optionalItem.isPresent()) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.not.actualize.data"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        return checkDataActuality(req, optionalItem.get());
    }

    private ResponseContext checkDataActuality(RequestContent req, LotDto lot) {
        if (lot.getId() > Long.parseLong(req.getRequestParameter("id")[0])) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.not.actual"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        return checkValidityOfNewBidder(req, lot);
    }

    private ResponseContext checkValidityOfNewBidder(RequestContent req, LotDto lot) {

        if (!UserStatus.VALID.equals(req.getSessionAttribute("status"))) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.not.user.blocked"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        if (req.getRequestParameter("type")[0].equals(ItemType.STRAIGHT.toString())) {
            return checkSumOfMoneyOnBidderAccount(req, lot);
        } else {
            return doReverseBid(req, lot);
        }
    }

    private ResponseContext checkSumOfMoneyOnBidderAccount(RequestContent req, LotDto lot) {
        final BigDecimal newPrice
                = lot.getPrice().add(BigDecimal.valueOf(Long.parseLong(req.getRequestParameter("bid")[0])).abs());
        final BigDecimal availableMoney = (BigDecimal) req.getSessionAttribute("account");
        final BigDecimal allowableSum
                = BigDecimal.valueOf(Long.parseLong(req.getContextParameter("allowable_debt"))).add(availableMoney);
        if (allowableSum.compareTo(newPrice) < 0) {
            req.setRequestAttribute("errorDoBidMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.do.bid.not.enough.money"));
            return ShowAllLotsCommand.INSTANCE.execute(req);
        }
        return doStraightBid(req, lot, newPrice);
    }

    private ResponseContext doStraightBid(RequestContent req, LotDto lot, BigDecimal newPrice) {

        final long possibleEndTime = GregorianCalendar.getInstance().getTimeInMillis()
                + Long.parseLong(req.getContextParameter("item_additional_show_time"));
        final long endTime = Math.max(lot.getEndTime(), possibleEndTime);
        LOT_SERVICE.doBid(lot.getItemId(), endTime, (Integer) req.getSessionAttribute("id"), newPrice);
        USER_SERVICE.updateAccount((Integer) req.getSessionAttribute("id"), newPrice);
        USER_SERVICE.updateAccount(Integer.parseInt(req.getRequestParameter
                ("previousBidOwnerId")[0]), lot.getPrice().negate());
        return ShowAllLotsCommand.INSTANCE.execute(req);
    }

    private ResponseContext doReverseBid(RequestContent req, LotDto item) {
        final BigDecimal newPrice
                = item.getPrice().subtract(BigDecimal.valueOf(Long.parseLong(req.getRequestParameter("bid")[0])).abs());
        final long possibleEndTime = GregorianCalendar.getInstance().getTimeInMillis()
                + Long.parseLong(req.getContextParameter("item_additional_show_time"));
        final long endTime = Math.max(item.getEndTime(), possibleEndTime);
        LOT_SERVICE.doBid(item.getItemId(), endTime, (Integer) req.getSessionAttribute("id"), newPrice);
        return ShowAllLotsCommand.INSTANCE.execute(req);
    }
}