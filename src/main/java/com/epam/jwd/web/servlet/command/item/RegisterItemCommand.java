package com.epam.jwd.web.servlet.command.item;

import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContext;
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
            return true;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {

        String itemName = req.getParameter("itemName");
        String itemDescribe = req.getParameter("itemDescribe");
        String itemType = req.getParameter("itemType");
        String itemPrice = req.getParameter("itemPrice");
        String minBid = req.getParameter("minBid");

        final long timeInMillis = GregorianCalendar.getInstance().getTimeInMillis();

        if (itemName == null || itemType == null) {
            req.setAttribute("errorMessage", "Not enough input data!");
            return ShowRegisterItemCommand.INSTANCE.execute(req);
        } else {
            service.register(itemName, itemDescribe, itemType, itemPrice, minBid, timeInMillis,
                    (String) req.getSession().getAttribute("userLogin"));
        }
        return null;
    }
}
