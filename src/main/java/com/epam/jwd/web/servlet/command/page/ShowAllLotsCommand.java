package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.cash.LotCash;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.Collections;
import java.util.List;

public enum ShowAllLotsCommand implements Command {
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

    @Override
    public ResponseContext execute(RequestContent req) {
        final List<LotDto> lots = LotCash.INSTANCE.getLots();
        req.setRequestAttribute(ITEMS_ATTRIBUTE_NAME, lots);
        return RESPONSE;
    }
}
