package com.epam.jwd.web.servlet.command.item;

import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowRegisterItemCommand;

import java.util.GregorianCalendar;

public enum RegisterItemCommand implements Command {
    INSTANCE;
    private final ItemService service = ItemServiceImpl.INSTANCE;
    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_MAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContent req) {

        String itemName = req.getRequestParameter("itemName")[0];
        String itemDescribe = req.getRequestParameter("itemDescribe")[0];
        String itemType = req.getRequestParameter("itemType")[0];
        String itemPrice = req.getRequestParameter("itemPrice")[0];

        service.register(itemName, itemDescribe, req.getSessionAttribute("id"), itemType, itemPrice);

        return RESPONSE;
    }
}
