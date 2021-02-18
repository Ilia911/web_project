package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.dao.impl.UserDaoImpl;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public enum UserServiceImpl implements UserService {
    INSTANCE;

    private static final String DUMMY_PASSWORD = "defaultPwd";
    private final UserDao userDao = new UserDaoImpl();


    @Override
    public Optional<List<UserDto>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> save(UserDto dto) {
        return Optional.empty();
    }

    @Override
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

    @Override
    public Optional<UserDto> register(String userLogin, String userPassword, String userName) {
        Optional<User> user = userDao.register(userLogin, BCrypt.hashpw(userPassword, BCrypt.gensalt()), userName);
        return user.map(this::convertToDto);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getAccount(),
                user.getRole(), user.getStatus());
    }

}
