package com.epam.jwd.web.servlet.command;

public enum  LogOutCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        return null;
    }
}
