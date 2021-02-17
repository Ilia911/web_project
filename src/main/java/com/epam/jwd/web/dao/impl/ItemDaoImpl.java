package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.CommonDao;
import com.epam.jwd.web.entity.Item;
import com.epam.jwd.web.entity.ItemType;
import com.epam.jwd.web.entity.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl implements CommonDao<Item> {

    private static final String TABLE_NAME = "item";
    private static final String ID_COLUMN_NAME = "id";
    private static final String NAME_COLUMN_NAME = "item_name";
    private static final String DESCRIBE_COLUMN_NAME = "item_describe";
    private static final String ID_OWNER_COLUMN_NAME = "owner_id";
    private static final String TYPE_COLUMN_NAME = "item_type";
    private static final String PRICE_COLUMN_NAME = "start_price";
    private static final String BID_COLUMN_NAME = "minimum_bid";
    private static final String STATUS_COLUMN_NAME = "item_status";
    private static final String FIND_ALL_ITEMS_SQL = "SELECT " + ID_COLUMN_NAME + ", " + NAME_COLUMN_NAME + ", "
            + DESCRIBE_COLUMN_NAME + ", " + ID_OWNER_COLUMN_NAME + ", " + TYPE_COLUMN_NAME + ", "
            + PRICE_COLUMN_NAME + ", " + BID_COLUMN_NAME + ", " + STATUS_COLUMN_NAME + " FROM " + TABLE_NAME;

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDaoImpl.class);

    @Override
    public Optional<List<Item>> findAll() {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(FIND_ALL_ITEMS_SQL)) {
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                items.add(readItem(resultSet));
            }
            return Optional.of(items);
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error("InterruptedException in Connection Pool!");
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("SQLException in Connection Pool!");
        }
        return Optional.empty();
    }

    private Item readItem(ResultSet resultSet) throws SQLException {
        return new Item(resultSet.getInt(ID_COLUMN_NAME),
                resultSet.getString(NAME_COLUMN_NAME),
                resultSet.getString(DESCRIBE_COLUMN_NAME),
                resultSet.getInt(ID_OWNER_COLUMN_NAME),
                ItemType.of(resultSet.getString(TYPE_COLUMN_NAME)),
                resultSet.getBigDecimal(PRICE_COLUMN_NAME),
                resultSet.getBigDecimal(BID_COLUMN_NAME),
                UserStatus.of(resultSet.getString(STATUS_COLUMN_NAME))
        );
    }

    @Override
    public Optional<Item> register(Item entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Item> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Item> update(Item entity) {
        return Optional.empty();
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
