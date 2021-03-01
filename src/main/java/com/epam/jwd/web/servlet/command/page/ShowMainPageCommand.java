package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

public enum ShowMainPageCommand implements Command {
    INSTANCE;

    private static final ResponseContext GUEST_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_MAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext CLIENT_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_CLIENT_MAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final ResponseContext ADMIN_RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_ADMIN_MAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public ResponseContext execute(RequestContent req) {
        if (req.getSessionAttribute("role") == null) {
            return GUEST_RESPONSE;
        }
        if (Role.CLIENT.equals(req.getSessionAttribute("role"))) {
            return CLIENT_RESPONSE;
        }
        if (Role.ADMIN.equals(req.getSessionAttribute("role"))) {
            return ADMIN_RESPONSE;
        }
        return GUEST_RESPONSE;
    }
}
