package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.model.UserStatus;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

public class UserDaoImplTest {

    private static final UserDao USER_DAO = UserDaoImpl.INSTANCE;
    private static final String EXISTED_USER_NAME = "admin";
    private static final int EXISTED_USER_ID = 2;

    @Test
    public void findByLogin_shouldReturnUser_ifSuchExists() {
        Assert.assertTrue(USER_DAO.findByLogin(EXISTED_USER_NAME).isPresent());
    }

    @Test
    public void findById_shouldReturnUser_ifSuchExists() {
        Assert.assertTrue(USER_DAO.findById(EXISTED_USER_ID).isPresent());
    }

    @Test
    public void save_shouldReturnUpdatedUser_ifSuchExists() {
        Assert.assertTrue(USER_DAO.save(EXISTED_USER_ID, EXISTED_USER_NAME, EXISTED_USER_NAME).isPresent());
    }

    @Test
    public void updateAccount_shouldUpdateUser_ifSuchExists() {
        Assert.assertTrue(USER_DAO.updateAccount(EXISTED_USER_ID, BigDecimal.ONE));
    }

    @Test
    public void changeStatus_shouldUpdateUserStatus_ifSuchExists() {
        Assert.assertTrue(USER_DAO.changeStatus(EXISTED_USER_ID, UserStatus.VALID));
    }

    @Test
    public void register_shouldRegisterUser_always() {
        Assert.assertTrue(USER_DAO.register("test_User","test password", "test name").isPresent());
    }

    @Test
    public void findAll_shouldReturnAllUsers_always() {
        Assert.assertTrue(USER_DAO.findAll().isPresent());
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        ConnectionPool.INSTANCE.destroy();
    }
}
