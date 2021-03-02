package com.epam.jwd.web.model;

import java.math.BigDecimal;

public class ItemDtoForList {

    private final long id;
    private final String name;
    private final String describe;
    private final int ownerId;
    private final ItemType type;
    private final BigDecimal price;
    private final long time;
    private final int bidOwnerId;

    public ItemDtoForList(long id, String name, String describe, int ownerId, ItemType type, BigDecimal price, long time, int bidOwnerId) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.ownerId = ownerId;
        this.type = type;
        this.price = price;
        this.time = time;
        this.bidOwnerId = bidOwnerId;
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

    public int getOwnerId() {
        return ownerId;
    }

    public ItemType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getTime() {
        return time;
    }

    public int getBidOwnerId() {
        return bidOwnerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDtoForList)) return false;

        ItemDtoForList that = (ItemDtoForList) o;

        if (id != that.id) return false;
        if (ownerId != that.ownerId) return false;
        if (time != that.time) return false;
        if (bidOwnerId != that.bidOwnerId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (describe != null ? !describe.equals(that.describe) : that.describe != null) return false;
        if (type != that.type) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (describe != null ? describe.hashCode() : 0);
        result = 31 * result + ownerId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + bidOwnerId;
        return result;
    }

    @Override
    public String toString() {
        return "ItemDtoForList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", ownerId=" + ownerId +
                ", type=" + type +
                ", price=" + price +
                ", time=" + time +
                ", bidOwnerId=" + bidOwnerId +
                '}';
    }
}



