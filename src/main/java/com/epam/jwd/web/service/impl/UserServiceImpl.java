package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.dao.impl.UserDaoImpl;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum UserServiceImpl implements UserService {
    INSTANCE;

    private static final String DUMMY_PASSWORD = "defaultPwd";
    private final UserDao userDao = new UserDaoImpl();


    @Override
    public Optional<List<UserDto>> findAll() {
        final Optional<List<User>> optionalUserList = userDao.findAll();
        return optionalUserList.map(users -> users.stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @Override
    public Optional<UserDto> save(int id, String newName, String newPassword) {

        final Optional<User> optionalUser = userDao.save(id, newName, BCrypt.hashpw(newPassword, BCrypt.gensalt()));

        if (optionalUser.isPresent()) {
            return optionalUser.map(this::convertToDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> login(String login, String password) {

        final Optional<User> user = userDao.findByLogin(login);
        if (!user.isPresent()) {
            BCrypt.checkpw(password, BCrypt.hashpw(DUMMY_PASSWORD, BCrypt.gensalt()));
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

    @Override
    public Optional<UserDto> findById(int id) {
        final Optional<User> optionalUser = userDao.findById(id);
        return optionalUser.map(this::convertToDto);
    }

    @Override
    public void updateAccount(int id, BigDecimal newUserAccount) {
        userDao.updateAccount(id, newUserAccount);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getAccount(),
                user.getRole(), user.getStatus());
    }
}
