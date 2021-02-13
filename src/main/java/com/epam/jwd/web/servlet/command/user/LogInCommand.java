package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContext;
import com.epam.jwd.web.servlet.command.ResponseContext;

public enum LogInCommand implements Command {
    INSTANCE;

    private static final ResponseContext res = new ResponseContext() {
        @Override
        public String getPage() {
            return "WEB-INF/jsp/main.jsp";
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    @Override
    public ResponseContext execute(RequestContext req) {

        return res;
    }
}
