package com.epam.jwd.web.servlet;

public class Main {
    static final String TABLE_NAME = "item";
    static final String ID_COLUMN_NAME = "id";
    static final String NAME_COLUMN_NAME = "item_name";
    static final String DESCRIBE_COLUMN_NAME = "item_describe";
    static final String ID_OWNER_COLUMN_NAME = "owner_id";
    static final String TYPE_COLUMN_NAME = "item_type";
    static final String PRICE_COLUMN_NAME = "start_price";
    static final String BID_COLUMN_NAME = "minimum_bid";
    static final String STATUS_COLUMN_NAME = "item_status";

    static final String FIND_ALL_ITEMS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", "
            + TYPE_COLUMN_NAME + ", " + PRICE_COLUMN_NAME + ", " + BID_COLUMN_NAME + " FROM " + TABLE_NAME
            + " WHERE (" + STATUS_COLUMN_NAME + " = ?)";

    public static void main(String[] args) {
        System.out.println(FIND_ALL_ITEMS_SQL);
    }
}
