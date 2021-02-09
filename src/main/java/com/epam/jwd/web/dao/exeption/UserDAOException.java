package com.epam.jwd.web.dao.exeption;

public class UserDAOException extends Exception {

    public UserDAOException(String message) {
        super(message);
    }
    public UserDAOException(Exception e) {
        super(e);
    }

}
