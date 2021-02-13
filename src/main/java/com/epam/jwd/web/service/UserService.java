package com.epam.jwd.web.service;

import com.epam.jwd.web.dao.impl.UserDaoImpl;
import com.epam.jwd.web.entity.User;
import com.epam.jwd.web.entity.UserDto;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class UserService implements CommonService<UserDto> {
    private static final String DUMMY_PASSWORD = "defaultPwd";
    private final UserDaoImpl userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public Optional<List<UserDto>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> save(UserDto dto) {
        return Optional.empty();
    }

//    public Optional<UserDto> login(String name, String password) {
//        final Optional<User> user = userDao.findByName(name);
//        if (!user.isPresent()) {
//            BCrypt.checkpw(password, DUMMY_PASSWORD); //to prevent timing attack
//            return Optional.empty();
//        }
//        final String realPassword = user.get().getPassword();
//        if (BCrypt.checkpw(password, realPassword)) {
//            return user.map(this::convertToDto);
//        } else {
//            return Optional.empty();
//        }
//    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getAccount(),
                user.getMail(), user.getRole(), user.getStatus());
    }
}
