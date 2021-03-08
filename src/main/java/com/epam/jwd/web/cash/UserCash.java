package com.epam.jwd.web.cash;

import com.epam.jwd.web.model.UserDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public enum UserCash {
    INSTANCE;

    private static final List<UserDto> usersDto = new LinkedList<>();

    public List<UserDto> getUsersDto() {
        return usersDto;
    }

    public Optional<UserDto> getUserDto(int id) {
        for (UserDto user : usersDto) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public void addUserDto(UserDto userDto){
        usersDto.add(userDto);
    }
}
