package com.epam.jwd.service.impl;

import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.dao.impl.UserDaoImpl;
import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.model.UserStatus;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserDaoImpl.class})
public class UserServiceImplTest {

    private static UserDao mockUserDao;

    private static final User TEST_USER = new User(1, "test login", "test password", "test name",
            BigDecimal.ONE, Role.CLIENT, UserStatus.VALID);

    @BeforeClass
    public static void init() {
        mockUserDao = mock(UserDaoImpl.class);
        Whitebox.setInternalState(UserDaoImpl.class, "INSTANCE", mockUserDao);
    }

    @Test
    public void findAll_shouldReturnOptionalListOfUsers_always() {
        when(mockUserDao.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        assertTrue(UserServiceImpl.INSTANCE.findAll().isPresent());
    }

//      todo: complete this methods
//    @Test
//    public void save_shouldUpdateExistedUser_always() {
//
//        when(mockUserDao.save(TEST_USER.getId(), TEST_USER.getName(),
//                TEST_USER.getPassword())).thenReturn(Optional.of(TEST_USER));
//
//        assertTrue(UserServiceImpl.INSTANCE.save(TEST_USER.getId(), TEST_USER.getName(),
//                TEST_USER.getPassword()).isPresent());
//    }

//    @Test
//    public void login_shouldReturnOptionalUser_ifSuchExists() {
//        when(mockUserDao.findByLogin(TEST_USER.getLogin())).thenReturn(Optional.of(TEST_USER));
//
//        assertTrue(UserServiceImpl.INSTANCE.login(TEST_USER.getLogin(), TEST_USER.getPassword()).isPresent());
//    }

//    @Test
//    public void register_shouldRegisterUser_ifSuchNotExists() {
//        when(mockUserDao.findByLogin(TEST_USER.getLogin())).thenReturn(Optional.of(TEST_USER));
//        when(mockUserDao.register(TEST_USER.getLogin(), TEST_USER.getPassword(), TEST_USER.getName()))
//                .thenReturn(Optional.of(TEST_USER));
//
//        assertTrue(UserServiceImpl.INSTANCE.register(TEST_USER.getLogin(), TEST_USER.getPassword(), TEST_USER.getName()).isPresent());
//    }

    @Test
    public void register_shouldReturnOptionalEmpty_ifUserWithSuchLoginExists() {
        when(mockUserDao.findByLogin(TEST_USER.getLogin())).thenReturn(Optional.empty());

        assertFalse(UserServiceImpl.INSTANCE.register(TEST_USER.getLogin(), TEST_USER.getPassword(),
                TEST_USER.getName()).isPresent());
    }

    @Test
    public void findById_shouldReturnOptionalUser_ifSuchExists() {
        when(mockUserDao.findById(TEST_USER.getId())).thenReturn(Optional.of(TEST_USER));

        assertTrue(UserServiceImpl.INSTANCE.findById(TEST_USER.getId()).isPresent());
    }

    @Test
    public void updateAccount_shouldUpdateAccount_always() {
        when(mockUserDao.updateAccount(TEST_USER.getId(), BigDecimal.ONE)).thenReturn(true);

        assertTrue(UserServiceImpl.INSTANCE.updateAccount(TEST_USER.getId(), BigDecimal.ONE));
    }

    @Test
    public void changeStatus_shouldChangeUserStatus_always() {
        when(mockUserDao.changeStatus(TEST_USER.getId(), TEST_USER.getStatus())).thenReturn(true);

        assertTrue(UserServiceImpl.INSTANCE.changeStatus(TEST_USER.getId(), TEST_USER.getStatus()));
    }

    @Test
    public void complete_() {
        when(mockUserDao.updateAccount(TEST_USER.getId(), BigDecimal.ONE)).thenReturn(true);

        assertTrue(UserServiceImpl.INSTANCE.updateAccount(TEST_USER.getId(), BigDecimal.ONE));
    }

}
