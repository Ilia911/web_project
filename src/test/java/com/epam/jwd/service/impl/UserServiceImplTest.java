package com.epam.jwd.service.impl;

import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.dao.impl.UserDaoImpl;
import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.model.UserStatus;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserDaoImpl.class})
public class UserServiceImplTest {

    private static UserDao mockUserDao;

    private static final User TEST_USER = new User(1,"test login", "test password", "test name",
            BigDecimal.ONE, Role.CLIENT, UserStatus.VALID);
    private static final UserService USER_SERVICE = UserServiceImpl.INSTANCE;

    @BeforeClass
    public static void init() {
        mockUserDao = mock(UserDaoImpl.class);
        Whitebox.setInternalState(UserDaoImpl.class, "INSTANCE", mockUserDao);
    }

    @Test
    public void findAll_shouldReturnOptionalListOfUsers_always() {
        when(mockUserDao.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        assertTrue(USER_SERVICE.findAll().isPresent());
    }

    @Test
    public void save_shouldUpdateExistedUser_ifSuchExists() {
        when(mockUserDao.save(TEST_USER.getId(), TEST_USER.getName(), TEST_USER.getPassword()))
                .thenReturn(Optional.of(TEST_USER));

        assertTrue(USER_SERVICE.save(TEST_USER.getId(), TEST_USER.getName(), TEST_USER.getPassword()).isPresent());
    }

    @Test
    public void login_shouldReturnOptionalUser_ifSuchExists() {
        when(mockUserDao.findByLogin(TEST_USER.getLogin())).thenReturn(Optional.of(TEST_USER));

        assertTrue(USER_SERVICE.login(TEST_USER.getLogin(), TEST_USER.getPassword()).isPresent());
    }

    @Test
    public void findAll_shouldReturnOptionalListOfUsers_always() {
        when(mockUserDao.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        assertTrue(UserDaoImpl.INSTANCE.findAll().isPresent());
    }

    @Test
    public void findAll_shouldReturnOptionalListOfUsers_always() {
        when(mockUserDao.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        assertTrue(UserDaoImpl.INSTANCE.findAll().isPresent());
    }

    @Test
    public void findAll_shouldReturnOptionalListOfUsers_always() {
        when(mockUserDao.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        assertTrue(UserDaoImpl.INSTANCE.findAll().isPresent());
    }
}
