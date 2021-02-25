package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemDtoForList;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl implements ItemDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDaoImpl.class);

    @Override
    public boolean register(String itemName, String itemDescribe, int itemType, long itemPrice,
                            long minBid, long time, int ownerId) {
        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(ItemSQL.REGISTER_ITEM_SQL);
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, itemDescribe);
            preparedStatement.setInt(3, ownerId);
            preparedStatement.setInt(4, itemType);
            preparedStatement.setLong(5, itemPrice);
            preparedStatement.setLong(6, minBid);
            preparedStatement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));

        }
        return true;
    }

    @Override
    public Optional<List<ItemDtoForList>> findAll(ItemStatus status) {
        List<ItemDtoForList> list = new ArrayList<>();

        try(final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(ItemSQL.FIND_ALL_ITEMS_SQL);
            preparedStatement.setInt(1, status.getInt());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                list.add(this.readItemForList(resultSet));
            }
            return Optional.of(list);
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    private ItemDtoForList readItemForList(ResultSet resultSet) throws SQLException {
        return new ItemDtoForList(resultSet.getInt(ItemSQL.ID_COLUMN_NAME),
                resultSet.getString(ItemSQL.NAME_COLUMN_NAME),
                ItemType.of(resultSet.getString(ItemSQL.TYPE_COLUMN_NAME)),
                resultSet.getBigDecimal(ItemSQL.PRICE_COLUMN_NAME),
                resultSet.getInt(ItemSQL.BID_COLUMN_NAME));
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
