package com.epam.jwd.web.servlet.command.user;

import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import com.epam.jwd.web.servlet.command.Command;
import com.epam.jwd.web.servlet.command.RequestContent;
import com.epam.jwd.web.servlet.command.ResponseContext;
import com.epam.jwd.web.servlet.command.page.ShowProfileCommand;

import java.util.Optional;

public enum SaveEditedUserCommand implements Command {
    INSTANCE;

    private static final UserService USER_SERVICE = UserServiceImpl.INSTANCE;

    @Override
    public ResponseContext execute(RequestContent req) {
        final Optional<UserDto> optionalUserDto = USER_SERVICE.save(Integer.parseInt(req.getRequestParameter("id")[0]),
                req.getRequestParameter("name")[0],
                req.getRequestParameter("password")[0]);

        if(optionalUserDto.isPresent()) {
            req.setSessionAttribute("name", optionalUserDto.get().getName());
            req.setRequestAttribute("user", optionalUserDto.get());
        }
        //todo: validate input data!
        return ShowProfileCommand.INSTANCE.execute(req);
    }
}
