package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.User;
import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.model.UserStatus;
import com.epam.jwd.web.observer.Publisher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * An object that implements this interface should connect with database using connection from
 * {@link com.epam.jwd.web.connection.ConnectionPool}, do CRUD actions and notify listeners.
 *
 * @author Ilia Eriomkin
 */
public interface UserDao extends Publisher<UserDto> {
    /**
     * Connect with database and return all users.
     * @return {@link Optional} with user list.
     */
    Optional<List<User>> findAll();

    /**
     * Connect with database.
     * Register new user.
     *
     * @param userLogin user login.
     * @param userPassword user password.
     * @param name user name.
     * @return {@link Optional} with new user.
     */
    Optional<User> register(String userLogin, String userPassword, String name);

    /**
     * Connect with database and find user by <tt>login</tt>.
     *
     * @param login user login.
     * @return {@link Optional} with user or {@link Optional#empty()} if <tt>login</tt>> is not valid.
     */
    Optional<User> findByLogin(String login);

    /**
     * Connect with database and find user by <tt>id</tt>.
     *
     * @param id user id.
     * @return {@link Optional} with user or {@link Optional#empty()} if <tt>id</tt>> is not valid.
     */
    Optional<User> findById(int id);

    /**
     * Connect with database and save new user name and password by id.
     *
     * @param id user <tt>id</tt>.
     * @param newName new user name.
     * @param newPassword new user password.
     * @return {@link Optional} with updated user or {@link Optional#empty()} if <tt>id</tt>> is not valid.
     */
    Optional<User> save(int id, String newName, String newPassword);

    /**
     * Connect with database, update user and notify listeners.
     *
     * @param userId user id.
     * @param subtractedSum sum that will be subtracted.
     * @return true if the action was successful.
     */
    boolean updateAccount(int userId, BigDecimal subtractedSum);

    /**
     * Connect with database, update user and notify listeners.
     *
     * @param id user id.
     * @param status new user status.
     * @return true if the action was successful.
     */
    boolean changeStatus(int id, UserStatus status);
}
