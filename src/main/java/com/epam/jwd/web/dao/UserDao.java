package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.User;
import com.epam.jwd.web.model.UserStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<List<User>> findAll();

    Optional<User> register(String userLogin, String userPassword, String name);

    Optional<User> update(User user);

    Optional<User> findByLogin(String login);

    boolean removeByLogin(String login);

    Optional<User> findById(int id);

    Optional<User> save(int id, String newName, String newPassword);

    void updateAccount(int id, BigDecimal newUserAccount);

    void changeStatus(int id, UserStatus status);
}
