package com.epam.jwd.web.model;

import java.math.BigDecimal;

public class LotDto {

    private final long id;
    private final long itemId;
    private final String name;
    private final String describe;
    private final int ownerId;
    private final ItemType type;
    private final BigDecimal price;
    private final long endTime;
    private final int bidOwnerId;

    @Override
    public String toString() {
        return "LotDto{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", ownerId=" + ownerId +
                ", type=" + type +
                ", price=" + price +
                ", endTime=" + endTime +
                ", bidOwnerId=" + bidOwnerId +
                '}';
    }

    public long getId() {
        return id;
    }

    public long getItemId() {
        return itemId;
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

    public long getEndTime() {
        return endTime;
    }

    public int getBidOwnerId() {
        return bidOwnerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LotDto)) return false;

        LotDto lotDto = (LotDto) o;

        if (id != lotDto.id) return false;
        if (itemId != lotDto.itemId) return false;
        if (ownerId != lotDto.ownerId) return false;
        if (endTime != lotDto.endTime) return false;
        if (bidOwnerId != lotDto.bidOwnerId) return false;
        if (name != null ? !name.equals(lotDto.name) : lotDto.name != null) return false;
        if (describe != null ? !describe.equals(lotDto.describe) : lotDto.describe != null) return false;
        if (type != lotDto.type) return false;
        return price != null ? price.equals(lotDto.price) : lotDto.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (itemId ^ (itemId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (describe != null ? describe.hashCode() : 0);
        result = 31 * result + ownerId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) (endTime ^ (endTime >>> 32));
        result = 31 * result + bidOwnerId;
        return result;
    }

    public LotDto(long id, long itemId, String name, String describe, int ownerId, ItemType type, BigDecimal price,
                  long endTime, int bidOwnerId) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.describe = describe;
        this.ownerId = ownerId;
        this.type = type;
        this.price = price;
        this.endTime = endTime;
        this.bidOwnerId = bidOwnerId;
    }
}



