package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContext;
import com.epam.jwd.web.servlet.command.ResponseContext;

public enum  ShowUserRegisterPageCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_REGISTER_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    @Override
    public ResponseContext execute(RequestContext req) {
        return RESPONSE;
    }
}
