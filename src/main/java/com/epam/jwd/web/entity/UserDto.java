package com.epam.jwd.web.entity;

import java.math.BigDecimal;

public class UserDto {
    private final Integer id;
    private final String login;
    private final String password;
    private final String name;
    private final BigDecimal account;
    private final String mail;
    private final Role role;
    private final Status status;

    public UserDto(Integer id, String login, String password, String name,
                   BigDecimal account, String mail, Role role, Status status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.account = account;
        this.mail = mail;
        this.role = role;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public String getMail() {
        return mail;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", account=" + account +
                ", mail='" + mail + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;

        UserDto userDto = (UserDto) o;

        if (id != null ? !id.equals(userDto.id) : userDto.id != null) return false;
        if (login != null ? !login.equals(userDto.login) : userDto.login != null) return false;
        if (password != null ? !password.equals(userDto.password) : userDto.password != null) return false;
        if (name != null ? !name.equals(userDto.name) : userDto.name != null) return false;
        if (account != null ? !account.equals(userDto.account) : userDto.account != null) return false;
        if (mail != null ? !mail.equals(userDto.mail) : userDto.mail != null) return false;
        if (role != userDto.role) return false;
        return status == userDto.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
