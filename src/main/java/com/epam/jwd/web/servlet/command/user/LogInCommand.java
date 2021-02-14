package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.entity.UserDto;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContext;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public enum LogInCommand implements Command {
    INSTANCE;

    private final UserService userService = UserService.INSTANCE;

    @Override
    public ResponseContext execute(RequestContext req) {
        final String login = String.valueOf(req.getAttribute("userLogin"));
        final String password = String.valueOf(req.getAttribute("userPassword"));

        final Optional<UserDto> optionalUserDto = userService.login(login, password);

        if (optionalUserDto.isPresent()) {
            final HttpSession session = req.getSession();
            session.setAttribute("name", optionalUserDto.get().getName());
            session.setAttribute("role", optionalUserDto.get().getRole());
            session.setAttribute("status", optionalUserDto.get().getStatus());
            return ShowMainPageCommand.INSTANCE.execute(req);
        } else {
            req.setAttribute("errorMessage", "Invalid credentials!");
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
