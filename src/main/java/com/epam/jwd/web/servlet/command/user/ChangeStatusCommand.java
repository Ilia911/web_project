package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.model.UserStatus;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowUsersCommand;

public enum ChangeStatusCommand implements Command {
    INSTANCE;

    private static final UserService USER_SERVICE = UserServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {
        USER_SERVICE.changeStatus(Integer.parseInt(req.getRequestParameter("id")[0]),
                UserStatus.valueOf(req.getRequestParameter("status")[0]).reverse());
        return ShowUsersCommand.INSTANCE.execute(req);
    }
}
