package com.epam.jwd.web.model;

public enum ItemType {
    STRAIGHT(1),
    REVERSE(2);

    private final int type;

    ItemType(int type) {
        this.type = type;
    }

    public static ItemType of(String type) {
        for (ItemType value : ItemType.values()) {
            if (value.type == Integer.parseInt(type)) {
                return value;
            }
         }
        return STRAIGHT;
    }
    public int getType() {
        return this.type;
    }

}
