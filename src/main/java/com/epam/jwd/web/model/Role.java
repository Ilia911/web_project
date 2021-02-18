package com.epam.jwd.web.model;

public enum Role {
    CLIENT(1),
    ADMIN(2),
    GUEST(3);

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
        return GUEST;
    }
}
