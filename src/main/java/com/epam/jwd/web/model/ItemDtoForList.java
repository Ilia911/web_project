package com.epam.jwd.web.model;

import java.math.BigDecimal;

public class ItemDtoForList {

    private final long id;
    private final String name;
    private final ItemType type;
    private final BigDecimal price;
    private final long time;


    public ItemDtoForList(long id, String name, ItemType type, BigDecimal price, long time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.time = time;
    }

    @Override
    public String toString() {
        return "ItemDtoForList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDtoForList)) return false;

        ItemDtoForList that = (ItemDtoForList) o;

        if (id != that.id) return false;
        if (time != that.time) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != that.type) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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
}



