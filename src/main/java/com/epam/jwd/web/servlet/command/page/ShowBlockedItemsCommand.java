package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.cash.UserCash;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ShowBlockedItemsCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {

        @Override
        public String getPage() {
            return Path.SHOW_BLOCKED_ITEMS_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final String ITEMS_ATTRIBUTE_NAME = "items";
    private static final ItemService ITEM_SERVICE = ItemServiceImpl.INSTANCE;
    private final static UserCash USER_CASH = UserCash.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {

        USER_CASH.actualizeUserData(req);

        return checkUserRole(req);
    }

    private ResponseContext checkUserRole(RequestContent req) {

        if (!Role.ADMIN.equals(req.getSessionAttribute("role"))) {
            req.setRequestAttribute("failedMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.not.admin"));
            return ShowMainPageCommand.INSTANCE.execute(req);
        }
        return showAllUsers(req);
    }

    private ResponseContext showAllUsers(RequestContent req) {
        final List<Item> items = ITEM_SERVICE.findAll(ItemStatus.BLOCKED).orElse(Collections.emptyList());
        req.setRequestAttribute(ITEMS_ATTRIBUTE_NAME, items);
        return RESPONSE;
    }
}
