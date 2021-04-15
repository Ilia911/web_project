package com.epam.jwd.web.cash;

import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.observer.Subscriber;
import com.epam.jwd.web.servlet.command.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Cash for users.
 *
 * @author Ilia Eriomkin
 */
public enum UserCash implements Subscriber<UserDto> {
    INSTANCE;

    private static final List<UserDto> USERS_DTO = new LinkedList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCash.class);

    public List<UserDto> getUsersDto() {
        return USERS_DTO;
    }

    public void addUserDto(UserDto newUser){
        for (UserDto user : USERS_DTO) {
            if (user.getId().equals(newUser.getId())) {
                return;
            }
        }
        USERS_DTO.add(newUser);
        LOGGER.info("User: " + newUser + " was added into user cash.");
    }

    @Override
    public void update(UserDto updatedUser) {
        for (UserDto userDto : USERS_DTO) {
            if (userDto.getId().equals(updatedUser.getId())) {
                USERS_DTO.remove(userDto);
                USERS_DTO.add(updatedUser);
                break;
            }
        }
    }

    public void actualizeUserData(RequestContent req) {

        if (req.getSessionAttribute("id") == null) {
            return;
        }

        final Optional<UserDto> optionalUserDto = getUserDto((Integer) req.getSessionAttribute("id"));

        if (optionalUserDto.isPresent()) {
            final UserDto userDto = optionalUserDto.get();

            req.setSessionAttribute("name", userDto.getName());
            req.setSessionAttribute("role", userDto.getRole());
            req.setSessionAttribute("status", userDto.getStatus());
            req.setSessionAttribute("account", userDto.getAccount());
        }
    }

    private Optional<UserDto> getUserDto(int id) {
        for (UserDto user : USERS_DTO) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
