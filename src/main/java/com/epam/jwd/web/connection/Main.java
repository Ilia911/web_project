package com.epam.jwd.web.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

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
    static final String END_TIME_NAME = "end_time";
    static final String BIDDER_ID_NAME = "bidder_id";
    static final String FIND_ALL_ITEMS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", "
            + DESCRIBE_COLUMN_NAME + ", " + ID_OWNER_COLUMN_NAME + ", " + TYPE_COLUMN_NAME + ", "
            + PRICE_COLUMN_NAME + ", " + BID_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME;
    static final String REGISTER_ITEM_SQL = "INSERT INTO " + TABLE_NAME + " ( " + NAME_COLUMN_NAME + ", "
            + DESCRIBE_COLUMN_NAME + ", " + ID_OWNER_COLUMN_NAME + ", " + TYPE_COLUMN_NAME + ", " + PRICE_COLUMN_NAME
            + ", " + BID_COLUMN_NAME  + ", " + END_TIME_NAME + ")  VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static void main(String[] args) throws SQLException, InterruptedException {
        System.out.println(new Date().getTime());
        final long time = new Date().getTime();
        ConnectionPool.INSTANCE.init();
        System.out.println(REGISTER_ITEM_SQL);
        final Connection connection = ConnectionPool.INSTANCE.retrieveConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_ITEM_SQL);
        preparedStatement.setString(1, "itemName");
        preparedStatement.setString(2, "itemDescribe");
        preparedStatement.setInt(3, 3);
        preparedStatement.setInt(4, 1);
        preparedStatement.setLong(5, 1454);
        preparedStatement.setLong(6, 24);
        preparedStatement.setLong(7, time);
        preparedStatement.executeUpdate();

    }

//        Locale chinalocale = new Locale("zh", "TW");
//        Locale germanyLocale = Locale.GERMAN;


//
//        System.out.println(Locale.getDefault());
//        Locale.setDefault(Locale.CANADA);
//        System.out.println(Locale.getDefault());
//        final Locale current = new Locale("be", "BY");
//        Locale.setDefault(chinalocale);
//        System.out.println(chinalocale.getDisplayLanguage());
//        final ResourceBundle bundle = ResourceBundle.getBundle("generalKeys", chinalocale);
//        System.out.println(bundle.getString("main.greeting"));
//
//        NumberFormat numberFormat = NumberFormat.getInstance(germanyLocale);


}
