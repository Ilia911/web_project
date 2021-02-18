package com.epam.jwd.web.model;

import java.math.BigDecimal;

public class ItemDto {

    private final Integer id;
    private final String name;
    private final String describe;
    private final Integer owner;
    private final ItemType type;
    private final BigDecimal price;
    private final BigDecimal bid;
    private final UserStatus status;

    public ItemDto(Integer id, String name, String describe, Integer owner,
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
        return "ItemDto{" +
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
        if (!(o instanceof ItemDto)) return false;

        ItemDto itemDto = (ItemDto) o;

        if (id != null ? !id.equals(itemDto.id) : itemDto.id != null) return false;
        if (name != null ? !name.equals(itemDto.name) : itemDto.name != null) return false;
        if (describe != null ? !describe.equals(itemDto.describe) : itemDto.describe != null) return false;
        if (owner != null ? !owner.equals(itemDto.owner) : itemDto.owner != null) return false;
        if (type != itemDto.type) return false;
        if (price != null ? !price.equals(itemDto.price) : itemDto.price != null) return false;
        if (bid != null ? !bid.equals(itemDto.bid) : itemDto.bid != null) return false;
        return status == itemDto.status;
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
