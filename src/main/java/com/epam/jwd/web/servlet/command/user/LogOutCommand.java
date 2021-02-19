package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;

public enum  LogOutCommand implements Command {
    INSTANCE;

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
        req.setSessionAttribute("login", null);
        req.setSessionAttribute("name", null);
        req.setSessionAttribute("role", null);
        req.setSessionAttribute("status", null);
        return ShowMainPageCommand.INSTANCE.execute(req);
    }
}
