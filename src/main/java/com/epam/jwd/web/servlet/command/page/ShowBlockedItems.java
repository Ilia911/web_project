package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

public enum ShowBlockedItems implements Command {
    INSTANCE;

    ResponseContext RESPONSE = new ResponseContext() {

        @Override
        public String getPage() {
            return Path.SHOW_BLOCKED_ITEMS_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContent req) {
        return RESPONSE;
    }
}
