package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.entity.ItemDto;
import com.epam.jwd.web.service.CommonService;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContext;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.Collections;
import java.util.List;

public enum  ShowAllItemsCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/items.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final String ITEMS_ATTRIBUTE_NAME = "items";
    private final CommonService<ItemDto> itemService;

    ShowAllItemsCommand() {
        itemService = new ItemService(new ItemDaoImpl());
    }


    @Override
    public ResponseContext execute(RequestContext req) {
        //todo: imply service
        final List<ItemDto> items = itemService.findAll().orElse(Collections.emptyList());
        req.setAttribute(ITEMS_ATTRIBUTE_NAME, items);
        return RESPONSE;
    }
}
