package com.epam.jwd.web.entity;

public enum Role {
    CLIENT(1),
    ADMIN(2);

    private final int role;

    Role(int role) {
        this.role = role;
    }

    public static Role of(String role) {
        for (Role value : Role.values()) {
            if (value.role == Integer.parseInt(role)) {
                return value;
            }
        }
        return CLIENT;
    }
}
