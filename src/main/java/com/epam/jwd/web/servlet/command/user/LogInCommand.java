package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;

import java.util.Optional;

public enum LogInCommand implements Command {
    INSTANCE;

    private final UserServiceImpl userService = UserServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {
        final String login = req.getRequestParameter("userLogin")[0];
        final String password = req.getRequestParameter("userPassword")[0];

        final Optional<UserDto> optionalUserDto = userService.login(login, password);

        if (optionalUserDto.isPresent()) {
            req.setSessionAttribute("id", optionalUserDto.get().getId());
            req.setSessionAttribute("login", login);
            req.setSessionAttribute("name", optionalUserDto.get().getName());
            req.setSessionAttribute("role", optionalUserDto.get().getRole());
            req.setSessionAttribute("status", optionalUserDto.get().getStatus());
            return ShowMainPageCommand.INSTANCE.execute(req);
        } else {
            req.setRequestAttribute("errorMessage", "Invalid credentials!");
            return new ResponseContext() {
                @Override
                public String getPage() {
                    return Path.SHOW_USER_LOGIN_PAGE;
                }

                @Override
                public boolean isRedirect() {
                    return true;
                }
            };
        }
    }
}
