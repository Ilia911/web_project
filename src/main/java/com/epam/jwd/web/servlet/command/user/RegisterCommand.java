package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public enum RegisterCommand implements Command {
    INSTANCE;

    private static final UserService USER_SERVICE = UserServiceImpl.INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_USER_REGISTER_AGAIN_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return true;
        }
    };

    @Override
    public ResponseContext execute(RequestContent req) {
        final ResourceBundle bundle
                = ResourceBundle.getBundle("generalKeys", (Locale) req.getSessionAttribute("locale"));

        Optional<UserDto> optionalUserDto = USER_SERVICE.register(req.getRequestParameter("login")[0],
                req.getRequestParameter("password")[0], req.getRequestParameter("name")[0]);
        if (optionalUserDto.isPresent()) {
            req.setRequestAttribute("successfulMessage", bundle.getString("message.register.success"));
        } else {
            req.setSessionAttribute("failedRegisterMessage", bundle.getString("message.register.failed"));
            return RESPONSE;
        }
        return ShowMainPageCommand.INSTANCE.execute(req);
    }
}
