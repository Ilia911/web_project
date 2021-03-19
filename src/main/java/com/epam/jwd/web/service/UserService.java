package com.epam.jwd.web.service;

import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.model.UserStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<List<UserDto>> findAll();

    Optional<UserDto> save(int id, String newName, String newPassword);

    Optional<UserDto> login(String login, String password);

    Optional<UserDto> register(String userLogin, String userPassword, String userName);

    Optional<UserDto> findById(int id);

    boolean updateAccount(int userId, BigDecimal subtractedSum);

    boolean changeStatus(int id, UserStatus status);

    boolean complete(LotDto lot);
}
