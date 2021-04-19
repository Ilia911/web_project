package com.epam.jwd.web.model;

/**
 * Item's status.
 *
 * @author Ilia Eriomkin
 */
public enum ItemStatus {
    VALID(1),
    BLOCKED(2),
    COMPLETED(3),
    DELETED(4);

    private final int status;

    ItemStatus(int status) {
        this.status = status;
    }

    public static ItemStatus of(String status) {
        for (ItemStatus value : ItemStatus.values()) {
            if (value.status == Integer.parseInt(status)) {
                return value;
            }
        }
        return BLOCKED;
    }

    public int getInt() {
        return this.status;
    }
}
