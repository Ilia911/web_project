package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public enum RegisterCommand implements Command {
    INSTANCE;


    @Override
    public ResponseContext execute(RequestContent req) {
        Optional<UserDto> optionalUserDto = UserServiceImpl.INSTANCE.register(req.getRequestParameter("userLogin")[0],
                req.getRequestParameter("userPassword")[0], req.getRequestParameter("userName")[0]);
        if (optionalUserDto.isPresent()) {
            req.setSessionAttribute("id", optionalUserDto.get().getId());
            req.setSessionAttribute("login", optionalUserDto.get().getLogin());
            req.setSessionAttribute("name", optionalUserDto.get().getName());
            req.setSessionAttribute("role", optionalUserDto.get().getRole());
            req.setSessionAttribute("status", optionalUserDto.get().getStatus());
        }

        return ShowMainPageCommand.INSTANCE.execute(req);
    }
}
