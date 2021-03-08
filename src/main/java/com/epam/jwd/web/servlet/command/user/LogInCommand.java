package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public enum LogInCommand implements Command {
    INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInCommand.class);

    private static final UserService userService = UserServiceImpl.INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_USER_LOGIN_AGAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    @Override
    public ResponseContext execute(RequestContent req) {
        final String login = req.getRequestParameter("login")[0];
        final String password = req.getRequestParameter("password")[0];
        final Optional<UserDto> optionalUserDto = userService.login(login, password);

        if (optionalUserDto.isPresent()) {
            setLoginAttributesIntoSession(req, optionalUserDto.get());
            return ShowMainPageCommand.INSTANCE.execute(req);
        } else {
            req.setSessionAttribute("name", "Guest");
            final ResourceBundle generalKeys = ResourceBundle.getBundle("generalKeys",
                    (Locale) req.getSessionAttribute("locale"));
            req.setSessionAttribute("errorLoginMessage", generalKeys.getString("message.login.error"));
            return RESPONSE;
        }
    }

    private void setLoginAttributesIntoSession (RequestContent req, UserDto userDto) {

        req.setSessionAttribute("id", userDto.getId());
        req.setSessionAttribute("login", userDto.getLogin());
        req.setSessionAttribute("name", userDto.getName());
        req.setSessionAttribute("role", userDto.getRole());
        req.setSessionAttribute("status", userDto.getStatus());
        req.setSessionAttribute("errorLoginMessage", null);
        LOGGER.info("User successfully logged in");
    }
}
