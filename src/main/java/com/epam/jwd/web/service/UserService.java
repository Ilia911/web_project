package com.epam.jwd.web.service;

import com.epam.jwd.web.dao.impl.UserDaoImpl;
import com.epam.jwd.web.entity.User;
import com.epam.jwd.web.entity.UserDto;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public enum UserService implements CommonService<UserDto> {
    INSTANCE;

    private static final String DUMMY_PASSWORD = "defaultPwd";
    private final UserDaoImpl userDao = new UserDaoImpl();


    @Override
    public Optional<List<UserDto>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> save(UserDto dto) {
        return Optional.empty();
    }

    public Optional<UserDto> login(String login, String password) {

        final Optional<User> user = userDao.findByLogin(login);
        if (!user.isPresent()) {
            BCrypt.checkpw(password, DUMMY_PASSWORD);
            return Optional.empty();
        }
        final String realPassword = user.get().getPassword();
        if (BCrypt.checkpw(password, realPassword)) {
            return user.map(this::convertToDto);
        } else {
            return Optional.empty();
        }
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getAccount(),
                user.getMail(), user.getRole(), user.getStatus());

    }

    public Optional<UserDto> register(String userLogin, String userPassword, String userName, String userEmail) {
        Optional<User> user = userDao.register(userLogin, userPassword, userName, userEmail);
        return user.map(this::convertToDto);
    }
}
