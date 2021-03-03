package com.epam.jwd.web.service;

import com.epam.jwd.web.model.UserDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<List<UserDto>> findAll();

    Optional<UserDto> save(UserDto userDto);

    Optional<UserDto> login(String login, String password);

    Optional<UserDto> register(String userLogin, String userPassword, String userName);

    Optional<UserDto> findById(int id);

    void updateAccount(Integer id, BigDecimal newUserAccount);
}
