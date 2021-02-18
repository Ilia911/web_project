package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<List<User>> findAll();

    Optional<User> register(String userLogin, String userPassword, String name);

    Optional<User> update(User user);

    Optional<User> findByLogin(String login);

    boolean removeByLogin(String login);
}
