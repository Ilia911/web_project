package com.epam.jwd.web.model;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Basic model for item.
 *
 * @author Ilia Eriomkin
 */
public class Item {
    private final long id;
    private final String name;
    private final String describe;
    private final int owner;
    private final ItemType type;
    private final BigDecimal price;
    private final ItemStatus status;
    private final long time;

    /**
     * Basic constructor.
     *
     * @param id       unique id that generated automatically by database.
     * @param name     item name.
     * @param describe item description.
     * @param owner    id of the owner.
     * @param type     type of the auction {@link ItemType}.
     * @param price    start item prise.
     * @param status   item status {@link ItemStatus}.
     * @param time     time of item registration in milliseconds from {@link GregorianCalendar#getTimeInMillis()}.
     */
    protected Item(long id, String name, String describe, int owner, ItemType type, BigDecimal price, ItemStatus status,
                   long time) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.owner = owner;
        this.type = type;
        this.price = price;
        this.status = status;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public int getOwner() {
        return owner;
    }

    public ItemType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (owner != item.owner) return false;
        if (time != item.time) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (describe != null ? !describe.equals(item.describe) : item.describe != null) return false;
        if (type != item.type) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        return status == item.status;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (describe != null ? describe.hashCode() : 0);
        result = 31 * result + owner;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", owner=" + owner +
                ", type=" + type +
                ", price=" + price +
                ", status=" + status +
                ", time=" + time +
                '}';
    }
}
