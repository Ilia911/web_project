package com.epam.jwd.web.model;

import java.math.BigDecimal;

public class User {
    private final Integer id;
    private final String login;
    private final String password;
    private final String name;
    private final BigDecimal account;
    private final Role role;
    private final UserStatus userStatus;

    public User(Integer id, String login, String password, String name, BigDecimal account, Role role, UserStatus userStatus) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.account = account;
        this.role = role;
        this.userStatus = userStatus;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return userStatus;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", account=" + account +
                ", role=" + role +
                ", status=" + userStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (account != null ? !account.equals(user.account) : user.account != null) return false;
        if (role != user.role) return false;
        return userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (userStatus != null ? userStatus.hashCode() : 0);
        return result;
    }
}
