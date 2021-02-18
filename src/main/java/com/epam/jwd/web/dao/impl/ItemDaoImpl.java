package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.model.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl implements ItemDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDaoImpl.class);

    @Override
    public boolean register(String itemName, String itemDescribe, int itemType, long itemPrice,
                            long minBid, long time, int ownerId) {
        try(final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(ItemSQL.REGISTER_ITEM_SQL);
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, itemDescribe);
            preparedStatement.setInt(3, ownerId);
            preparedStatement.setInt(4, itemType);
            preparedStatement.setLong(5, itemPrice);
            preparedStatement.setLong(6, minBid);
            preparedStatement.setLong(7, time);
            preparedStatement.executeUpdate();
//            INSERT INTO item ( item_name, item_describe, owner_id, item_type, start_price, minimum_bid, end_time)  VALUES (?, ?, ?, ?, ?, ?, ?)
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));

        }
        return false;
    }


    @Override
    public Optional<List<Item>> findAll() {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(ItemSQL.FIND_ALL_ITEMS_SQL)) {
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
        return new Item(resultSet.getInt(ItemSQL.ID_COLUMN_NAME),
                resultSet.getString(ItemSQL.NAME_COLUMN_NAME),
                resultSet.getString(ItemSQL.DESCRIBE_COLUMN_NAME),
                resultSet.getInt(ItemSQL.ID_OWNER_COLUMN_NAME),
                ItemType.of(resultSet.getString(ItemSQL.TYPE_COLUMN_NAME)),
                resultSet.getBigDecimal(ItemSQL.PRICE_COLUMN_NAME),
                resultSet.getBigDecimal(ItemSQL.BID_COLUMN_NAME),
                UserStatus.of(resultSet.getString(ItemSQL.STATUS_COLUMN_NAME))
        );
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
