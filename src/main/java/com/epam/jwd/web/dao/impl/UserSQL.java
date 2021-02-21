package com.epam.jwd.web.dao.impl;

class UserSQL {
    private UserSQL() {}
    static final String TABLE_NAME = "auction_user";
    static final String ID_COLUMN_NAME = "id";
    static final String LOGIN_COLUMN_NAME = "user_login";
    static final String PASSWORD_COLUMN_NAME = "user_password";
    static final String NAME_COLUMN_NAME = "user_name";
    static final String ACCOUNT_COLUMN_NAME = "user_account";
    static final String ROLE_COLUMN_NAME = "user_role";
    static final String STATUS_COLUMN_NAME = "user_status";

    static final String FIND_ALL_USERS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + LOGIN_COLUMN_NAME + ", "
            + PASSWORD_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", " + ACCOUNT_COLUMN_NAME + ", "
            + ROLE_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME;

    static final String FIND_USER_BY_LOGIN_SQL = "SELECT " + ID_COLUMN_NAME + ", " + LOGIN_COLUMN_NAME + ", "
            + PASSWORD_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", " + ACCOUNT_COLUMN_NAME + ", "
            + ROLE_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME
            + " WHERE " + LOGIN_COLUMN_NAME + " = ?";

    static final String REGISTER_USER_SQL = "INSERT INTO " + TABLE_NAME + " ( " + LOGIN_COLUMN_NAME + ", "
            + PASSWORD_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ")  VALUES (?, ?, ?)";

    static final String UPDATE_USER_SQL = "UPDATE " + TABLE_NAME + " SET " + LOGIN_COLUMN_NAME + " = ?, "
            + PASSWORD_COLUMN_NAME + " = ?, " + NAME_COLUMN_NAME + " = ?, " + ACCOUNT_COLUMN_NAME  + " = ?, "
            + ROLE_COLUMN_NAME  + " = ?, " + STATUS_COLUMN_NAME + " = ?, WHERE (" + ID_COLUMN_NAME + " = ?)";

}
