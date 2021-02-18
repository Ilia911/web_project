package com.epam.jwd.web.model;

import java.math.BigDecimal;

public class Item {
    private final Integer id;
    private final String name;
    private final String describe;
    private final Integer owner;
    private final ItemType type;
    private final BigDecimal price;
    private final BigDecimal bid;
    private final UserStatus status;

    public Item(Integer id, String name, String describe, Integer owner,
                ItemType type, BigDecimal price, BigDecimal bid, UserStatus status) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.owner = owner;
        this.type = type;
        this.price = price;
        this.bid = bid;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getOwner() {
        return owner;
    }

    public ItemType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public UserStatus getStatus() {
        return status;
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
                ", bid=" + bid +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (describe != null ? !describe.equals(item.describe) : item.describe != null) return false;
        if (owner != null ? !owner.equals(item.owner) : item.owner != null) return false;
        if (type != item.type) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (bid != null ? !bid.equals(item.bid) : item.bid != null) return false;
        return status == item.status;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (describe != null ? describe.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }


}
