package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowMainPageCommand;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public enum RegisterCommand implements Command {
    INSTANCE;


    @Override
    public ResponseContext execute(RequestContent req) {
        final ResourceBundle bundle
                = ResourceBundle.getBundle("generalKeys", (Locale) req.getSessionAttribute("locale"));

        Optional<UserDto> optionalUserDto = UserServiceImpl.INSTANCE.register(req.getRequestParameter("login")[0],
                req.getRequestParameter("password")[0], req.getRequestParameter("name")[0]);
        if (optionalUserDto.isPresent()) {
            req.setRequestAttribute("successfulMessage", bundle.getString("message.register.success"));
        } else {
            req.setSessionAttribute("failedRegisterMessage", bundle.getString("message.register.failed"));
            req.setInvalidateSession(true);
            return new ResponseContext() {
                @Override
                public String getPage() {
                    return "/controller?command=show_register";
                }

                @Override
                public boolean isRedirect() {
                    return true;
                }
            };
        }
        return ShowMainPageCommand.INSTANCE.execute(req);
    }
}
