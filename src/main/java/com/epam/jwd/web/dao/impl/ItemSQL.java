package com.epam.jwd.web.dao.impl;

class ItemSQL {
    private ItemSQL() {}

    static final String TABLE_NAME = "item";
    static final String ID_COLUMN_NAME = "id";
    static final String NAME_COLUMN_NAME = "item_name";
    static final String DESCRIBE_COLUMN_NAME = "item_describe";
    static final String ID_OWNER_COLUMN_NAME = "owner_id";
    static final String TYPE_COLUMN_NAME = "item_type";
    static final String PRICE_COLUMN_NAME = "start_price";
    static final String BID_COLUMN_NAME = "minimum_bid";
    static final String STATUS_COLUMN_NAME = "item_status";
    static final String END_TIME_NAME = "end_time";
    static final String BIDDER_ID_NAME = "bidder_id";

    static final String FIND_ALL_VALID_ITEMS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", "
            + DESCRIBE_COLUMN_NAME + ", " + ID_OWNER_COLUMN_NAME + ", " + TYPE_COLUMN_NAME + ", "
            + PRICE_COLUMN_NAME + ", " + BID_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME
            + " WHERE " + STATUS_COLUMN_NAME + " = 1";

    static final String FIND_ALL_BLOCKED_ITEMS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", "
            + DESCRIBE_COLUMN_NAME + ", " + ID_OWNER_COLUMN_NAME + ", " + TYPE_COLUMN_NAME + ", "
            + PRICE_COLUMN_NAME + ", " + BID_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME
            + " WHERE " + STATUS_COLUMN_NAME + " = 2";

    static final String FIND_ALL_DELETED_ITEMS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", "
            + DESCRIBE_COLUMN_NAME + ", " + ID_OWNER_COLUMN_NAME + ", " + TYPE_COLUMN_NAME + ", "
            + PRICE_COLUMN_NAME + ", " + BID_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME
            + " WHERE " + STATUS_COLUMN_NAME + " = 3";

    static final String REGISTER_ITEM_SQL = "INSERT INTO " + TABLE_NAME + " ( " + NAME_COLUMN_NAME + ", "
            + DESCRIBE_COLUMN_NAME + ", " + ID_OWNER_COLUMN_NAME + ", " + TYPE_COLUMN_NAME + ", " + PRICE_COLUMN_NAME
            + ", " + BID_COLUMN_NAME  + ", " + END_TIME_NAME + ")  VALUES (?, ?, ?, ?, ?, ?, ?)";

    static final String UPDATE_ITEM_SQL = "UPDATE " + TABLE_NAME + " SET " + NAME_COLUMN_NAME + " = ?, "
            + DESCRIBE_COLUMN_NAME + " = ?, " + TYPE_COLUMN_NAME + " = ?, " + PRICE_COLUMN_NAME
            + " = ?, " + BID_COLUMN_NAME  + " = ?, " + END_TIME_NAME + " = ?, WHERE (" + ID_COLUMN_NAME + " = ?)";

    static final String DO_BID_SQL = "UPDATE " + TABLE_NAME + " SET " +  END_TIME_NAME  + " = ?, "
            + BIDDER_ID_NAME + " = ?, WHERE (" + ID_COLUMN_NAME + " = ?)";


}
