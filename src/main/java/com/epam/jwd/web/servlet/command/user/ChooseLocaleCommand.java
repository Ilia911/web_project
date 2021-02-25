package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;

import java.util.Locale;

public enum ChooseLocaleCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {
        final String[] locale = req.getRequestParameter("locale")[0].split("_");
        req.setSessionAttribute("locale", new Locale(locale[0], locale[1]));
        return ShowMainPageCommand.INSTANCE.execute(req);
    }
}
