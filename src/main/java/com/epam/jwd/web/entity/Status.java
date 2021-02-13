package com.epam.jwd.web.entity;

public enum Status {
    VALID(1),
    BLOCKED(2);

    private final int status;

    Status(int status) {
        this.status = status;
    }

    public static Status of(String status) {
        for (Status value : Status.values()) {
            if (value.status == Integer.parseInt(status)) {
                return value;
            }
        }
        return BLOCKED;
    }
}
