package com.epam.jwd.web.dao;

import com.epam.jwd.web.dao.exeption.UserDAOException;
import com.epam.jwd.web.entity.User;

public interface UserDAO {
    boolean authorization(String login, String password) throws UserDAOException;
    boolean registration(User newUser) throws UserDAOException;
}
