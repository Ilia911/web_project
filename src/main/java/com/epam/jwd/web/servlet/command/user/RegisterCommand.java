package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.entity.UserDto;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContext;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public enum RegisterCommand implements Command {
    INSTANCE;


    @Override
    public ResponseContext execute(RequestContext req) {
        Optional<UserDto> optionalUserDto = UserService.INSTANCE.register(req.getParameter("userLogin"),
                req.getParameter("userPassword"), req.getParameter("userName"), req.getParameter("userEmail"));
        if (optionalUserDto.isPresent()) {
            final HttpSession session = req.getSession();
            session.setAttribute("name", optionalUserDto.get().getName());
            session.setAttribute("role", optionalUserDto.get().getRole());
            session.setAttribute("status", optionalUserDto.get().getStatus());
        }

        return ShowMainPageCommand.INSTANCE.execute(req);
    }
}
