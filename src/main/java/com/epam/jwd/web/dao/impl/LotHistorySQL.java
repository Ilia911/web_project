package com.epam.jwd.web.dao.impl;

class LotHistorySQL {
    private LotHistorySQL(){}

    static final String TABLE_NAME = "lot_history";
    static final String ID_COLUMN_NAME = "id";
    static final String ITEM_ID_COLUMN_NAME = "item_id";
    static final String BID_TIME_COLUMN_NAME = "bid_time";
    static final String ID_BID_OWNER_COLUMN_NAME = "bid_owner_id";
    static final String CURRENT_PRICE_COLUMN_NAME = "current_price";

    static final String DO_BID_SQL = "INSERT INTO " + TABLE_NAME + " ( " + ITEM_ID_COLUMN_NAME + ", "
            + BID_TIME_COLUMN_NAME + ", " + ID_BID_OWNER_COLUMN_NAME + ", " + CURRENT_PRICE_COLUMN_NAME +  ")  VALUES (?, ?, ?, ?)";

}
