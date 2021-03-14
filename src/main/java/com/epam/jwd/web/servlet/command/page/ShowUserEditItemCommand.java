package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.Optional;

public enum ShowUserEditItemCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_USER_EDIT_ITEM_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ItemService ITEM_SERVICE = ItemServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {
        final Optional<Item> optionalItem
                = ITEM_SERVICE.findItemById(Long.parseLong(req.getRequestParameter("id")[0]));

        optionalItem.ifPresent(item -> req.setRequestAttribute("item", item));

        return RESPONSE;
    }
}
