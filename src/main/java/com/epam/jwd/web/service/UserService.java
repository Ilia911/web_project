package com.epam.jwd.web.service;

import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.UserDto;
import com.epam.jwd.web.model.UserStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * An object that implements this interface should check and evaluate
 * input data and send request to a {@link com.epam.jwd.web.dao.UserDao} object.
 * Then if needed it should do some logic with response data.
 *
 * @author Ilia Eriomkin
 */
public interface UserService {

    /**
     * Connect with the {@link com.epam.jwd.web.dao.UserDao} object.
     * Find all {@link com.epam.jwd.web.model.UserDto} objects.
     *
     * @return all {@link com.epam.jwd.web.model.UserDto} objects.
     */
    Optional<List<UserDto>> findAll();

    /**
     * Connect with the {@link com.epam.jwd.web.dao.UserDao} object and
     * save new user name and new password.
     *
     * @param id          id of the certain {@link com.epam.jwd.web.model.User} that should be updated.
     * @param newName     new user name.
     * @param newPassword new user password.
     * @return {@link java.util.Optional} with updated user.
     */
    Optional<UserDto> save(int id, String newName, String newPassword);

    /**
     * Connect with the {@link com.epam.jwd.web.dao.UserDao} object and
     * returns {@link com.epam.jwd.web.model.UserDto} object if <tt>login</tt> and <tt>password</tt> are valid.
     *
     * @param login    user login from client request.
     * @param password user password from client request.
     * @return {@link java.util.Optional} with certain user or
     * {@link Optional#empty()} if <tt>login</tt> or <tt>password</tt> aren't valid.
     */
    Optional<UserDto> login(String login, String password);

    /**
     * Connect with the {@link com.epam.jwd.web.dao.UserDao} object, register on server new user and
     * return {@link com.epam.jwd.web.model.UserDto} object if <tt>login</tt> and <tt>password</tt> are valid.
     *
     * @param userLogin    user login.
     * @param userPassword user password.
     * @param userName     user name.
     * @return {@link java.util.Optional} with certain user or
     * {@link Optional#empty()} if <tt>login</tt> or <tt>password</tt> aren't valid.
     */
    Optional<UserDto> register(String userLogin, String userPassword, String userName);

    /**
     * Connect with the {@link com.epam.jwd.web.dao.UserDao} object and
     * return {@link com.epam.jwd.web.model.UserDto} object if <tt>id</tt> is valid.
     *
     * @param id id of the certain user.
     * @return {@link java.util.Optional} with certain user or
     * {@link Optional#empty()} if <tt>id</tt> is not valid.
     */
    Optional<UserDto> findById(int id);

    /**
     * Connect with the {@link com.epam.jwd.web.dao.UserDao} object and update user account by user <tt>id</tt>.
     *
     * @param userId id of the certain user.
     * @param subtractedSum sum of money that should be subtracted.
     * @return true if user id is valid and user account was successfully updated.
     */
    boolean updateAccount(int userId, BigDecimal subtractedSum);

    /**
     * Connect with the {@link com.epam.jwd.web.dao.UserDao} object and change user status by user <tt>id</tt> and
     * <tt>status</tt>.
     *
     * @param id user id.
     * @param status new user status.
     * @return true if user id is valid and user data were successfully updated.
     */
    boolean changeStatus(int id, UserStatus status);

    /**
     * If someone did bid on this <tt>lot</tt> then
     * connect with the {@link com.epam.jwd.web.dao.UserDao} object and update lot owner account.
     *
     * @param lot completed lot.
     * @return true if someone did bid.
     */
    boolean complete(LotDto lot);
}
