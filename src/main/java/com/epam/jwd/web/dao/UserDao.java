package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.User;
import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.model.UserStatus;
import com.epam.jwd.web.observer.Publisher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao extends Publisher<UserDto> {
    Optional<List<User>> findAll();

    Optional<User> register(String userLogin, String userPassword, String name);

    Optional<User> update(User user);

    Optional<User> findByLogin(String login);

    Optional<User> findById(int id);

    Optional<User> save(int id, String newName, String newPassword);

    void updateAccount(int userId, BigDecimal subtractedSum);

    void changeStatus(int id, UserStatus status);
}
