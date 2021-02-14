package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.entity.UserDto;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContext;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;

import java.util.Optional;

public enum RegisterCommand implements Command {
    INSTANCE;


    @Override
    public ResponseContext execute(RequestContext req) {
        Optional<UserDto> userDto = UserService.INSTANCE.register(req.getParameter("userLogin"),
                req.getParameter("userPassword"), req.getParameter("userName"), req.getParameter("userEmail"));

        return ShowMainPageCommand.INSTANCE.execute(req);
    }
}
