package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContext;
import com.epam.jwd.web.servlet.command.ResponseContext;

public enum RegisterItemCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        return null;
    }
}
