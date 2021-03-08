package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public enum ShowUserItemsCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_USER_ITEMS_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ItemService ITEM_SERVICE = ItemServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {
        final Optional<List<LotDto>> optionalUserItems = ITEM_SERVICE
                .findItemsByUserId((Integer) req.getSessionAttribute("id"));
        if (optionalUserItems.isPresent()) {
            req.setRequestAttribute("items", optionalUserItems.get());
        } else {
            req.setRequestAttribute("errorUserItemsMessage", ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale")).getString("message.user.items.empty"));
        }
        return RESPONSE;
    }
}
