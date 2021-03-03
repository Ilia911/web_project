package com.epam.jwd.web.model;

import java.math.BigDecimal;

public enum ItemFactory {
    INSTANCE;

    public Item createItem(long id, String name, String describe, int owner, ItemType type, BigDecimal price,
                           ItemStatus status, long time) {
        return new Item(id, name, describe, owner, type, price, status, time);
    }
}
