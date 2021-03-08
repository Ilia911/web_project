package com.epam.jwd.web.cash;

import com.epam.jwd.web.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum UserCash {
    INSTANCE;

    private final List<User> users = new ArrayList<>();


    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(int id) {
        return users.get(id);
    }

}
