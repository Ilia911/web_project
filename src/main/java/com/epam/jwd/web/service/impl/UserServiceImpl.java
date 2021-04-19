package com.epam.jwd.web.service.impl;

import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.dao.impl.UserDaoImpl;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.model.UserStatus;
import com.epam.jwd.web.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum UserServiceImpl implements UserService {
    INSTANCE;

    private static final String DUMMY_PASSWORD = "defaultPwd";
    private static final UserDao USER_DAO = UserDaoImpl.INSTANCE;


    @Override
    public Optional<List<UserDto>> findAll() {
        final Optional<List<User>> optionalUserList = USER_DAO.findAll();
        return optionalUserList.map(users -> users.stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @Override
    public Optional<UserDto> save(int id, String newName, String newPassword) {

        final Optional<User> optionalUser = USER_DAO.save(id, newName, BCrypt.hashpw(newPassword, BCrypt.gensalt()));

        if (optionalUser.isPresent()) {
            return optionalUser.map(this::convertToDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDto> login(String login, String password) {

        final Optional<User> user = USER_DAO.findByLogin(login);
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

        final Optional<User> optionalUser = USER_DAO.findByLogin(userLogin);
        if (optionalUser.isPresent()) {
            return Optional.empty();
        }

        Optional<User> user = USER_DAO.register(userLogin, BCrypt.hashpw(userPassword, BCrypt.gensalt()), userName);
        return user.map(this::convertToDto);
    }

    @Override
    public Optional<UserDto> findById(int id) {
        final Optional<User> optionalUser = USER_DAO.findById(id);
        return optionalUser.map(this::convertToDto);
    }

    @Override
    public boolean updateAccount(int userId, BigDecimal subtractedSum) {
        return USER_DAO.updateAccount(userId, subtractedSum);
    }

    @Override
    public boolean changeStatus(int id, UserStatus status) {
        return USER_DAO.changeStatus(id, status);
    }

    @Override
    public boolean complete(LotDto lot) {
        if (lot.getOwnerId() != lot.getBidOwnerId()) {
            final Optional<UserDto> optionalItemOwner = findById(lot.getOwnerId());
            return updateAccount(lot.getOwnerId(), optionalItemOwner.get().getAccount().add(lot.getPrice()));
        }
        return false;
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getLogin(), null, user.getName(), user.getAccount(),
                user.getRole(), user.getStatus());
    }
}
