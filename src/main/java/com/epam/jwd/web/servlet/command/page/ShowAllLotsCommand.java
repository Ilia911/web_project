package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.cash.LotCash;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

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
    private static final String SHOWED_ITEMS_ON_PAGE = "showedItems";
    private static final String SHOWED_PAGE = "showedPage";
    private static final String TOTAL_AMOUNT_OF_ITEMS = "allItems";
    private static final String TOTAL_AMOUNT_OF_PAGES = "allPages";
    private static final int FIRST_SHOWED_PAGE = 1;
    private static final int ORIGIN_NUMBER_OF_SHOWED_ITEMS_ON_ONE_PAGE = 5;

    @Override
    public ResponseContext execute(RequestContent req) {
        final List<LotDto> lots = LotCash.INSTANCE.getLots();
        final int[] parameters = defineParameters(req);
        int fromIndex = (parameters[1] - 1) * parameters[0];
        int toIndex = Math.min((parameters[0] * parameters[1]), lots.size() - 1);
        final List<LotDto> lotSublist = lots.subList(fromIndex, toIndex);

        req.setRequestAttribute(SHOWED_ITEMS_ON_PAGE, parameters[0]);
        req.setRequestAttribute(SHOWED_PAGE, parameters[1]);
        req.setRequestAttribute(ITEMS_ATTRIBUTE_NAME, lotSublist);
        req.setRequestAttribute(TOTAL_AMOUNT_OF_ITEMS, lots.size());
        req.setRequestAttribute(TOTAL_AMOUNT_OF_PAGES, Math.floorDiv(lots.size(), parameters[0]));
        return RESPONSE;
    }

    private int[] defineParameters(RequestContent req) {
        final int[] parameters = new int[2];

        if (req.getRequestParameter(SHOWED_ITEMS_ON_PAGE) == null) {
            parameters[0] = ORIGIN_NUMBER_OF_SHOWED_ITEMS_ON_ONE_PAGE;
        } else {
            parameters[0] = Integer.parseInt(req.getRequestParameter(SHOWED_ITEMS_ON_PAGE)[0]);
        }

        if (req.getRequestParameter(SHOWED_PAGE) == null) {
            parameters[1] = FIRST_SHOWED_PAGE;
        } else {
            parameters[1] = Integer.parseInt(req.getRequestParameter(SHOWED_PAGE)[0]);
        }

        return parameters;
    }
}
