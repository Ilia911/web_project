package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.model.ItemDto;
import com.epam.jwd.web.service.CommonService;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContext;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.Collections;
import java.util.List;

public enum  ShowAllItemsCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_ALL_ITEMS_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final String ITEMS_ATTRIBUTE_NAME = "items";
    private final ItemService itemService = ItemServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        final List<ItemDto> items = itemService.findAll().orElse(Collections.emptyList());
        req.setAttribute(ITEMS_ATTRIBUTE_NAME, items);
        return RESPONSE;
    }
}
