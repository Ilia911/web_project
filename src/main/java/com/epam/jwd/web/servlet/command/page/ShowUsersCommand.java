package com.epam.jwd.web.servlet.command.page;

import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.Path;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;

import java.util.List;
import java.util.Optional;

public enum ShowUsersCommand implements Command {
    INSTANCE;

    private static final ResponseContext RESPONSE = new ResponseContext() {
        @Override
        public String getPage() {
            return Path.SHOW_USERS_PAGE;
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    private static final UserService USER_SERVICE = UserServiceImpl.INSTANCE;
    private static final String USER_LIST_NAME = "users";

    @Override
    public ResponseContext execute(RequestContent req) {
        final Optional<List<UserDto>> optionalUserDtoList = USER_SERVICE.findAll();

        if (!optionalUserDtoList.isPresent()) {
            return ShowMainPageCommand.INSTANCE.execute(req);
        }

        req.setRequestAttribute(USER_LIST_NAME, optionalUserDtoList.get());

        return RESPONSE;
    }
}
