package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.Optional;

public enum ShowProfileCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_PROFILE_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final UserService USER_SERVICE = UserServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {

        final Optional<UserDto> optionalUserDto
                = USER_SERVICE.findById((Integer)req.getSessionAttribute("id"));
        if (!optionalUserDto.isPresent()) {
            return ShowMainPageCommand.INSTANCE.execute(req);
        }

        req.setRequestAttribute("user", optionalUserDto.get());

        return RESPONSE;
    }
}
