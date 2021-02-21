package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

public enum  ShowRegisterItemCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_REGISTER_ITEM_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };
    @Override
    public ResponseContext execute(RequestContent req) {
        Role userRole = (Role) req.getSessionAttribute("role");
        if (Role.CLIENT.equals(userRole) || Role.ADMIN.equals(userRole) ) {
            return RESPONSE;
        }
        return ShowMainPageCommand.INSTANCE.execute(req);

    }
}
