package com.epam.jwd.web.servlet.command.item;

import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowUserItemsCommand;

public enum SaveEditedItemCommand implements Command {
    INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {

        return ShowUserItemsCommand.INSTANCE.execute(req);
    }
}
