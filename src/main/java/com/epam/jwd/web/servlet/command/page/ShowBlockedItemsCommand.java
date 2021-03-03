package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.model.ItemDtoForList;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.Collections;
import java.util.List;

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
    private final ItemService itemService = ItemServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {

        final List<ItemDtoForList> items = itemService.findAll(ItemStatus.BLOCKED).orElse(Collections.emptyList());
        req.setRequestAttribute(ITEMS_ATTRIBUTE_NAME, items);
        return RESPONSE;
    }
}
