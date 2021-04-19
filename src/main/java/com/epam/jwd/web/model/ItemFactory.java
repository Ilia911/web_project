package com.epam.jwd.web.model;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Factory for items.
 *
 * @author Ilia Eriomkin
 */
public enum ItemFactory {
    INSTANCE;

    /**
     * Create new item.
     *
     * @param id       unique id that generated automatically by database.
     * @param name     item name.
     * @param describe item description.
     * @param owner    id of the owner.
     * @param type     type of the auction {@link ItemType}.
     * @param price    start item prise.
     * @param status   item status {@link ItemStatus}.
     * @param time     time of item registration in milliseconds from {@link GregorianCalendar#getTimeInMillis()}.
     * @return new item.
     */
    public Item createItem(long id, String name, String describe, int owner, ItemType type, BigDecimal price,
                           ItemStatus status, long time) {
        return new Item(id, name, describe, owner, type, price, status, time);
    }
}
