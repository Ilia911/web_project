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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl implements ItemDao {
    private static final String FIND_ALL_VALID_ITEMS_SQL = "SELECT i.id, i.item_name, i.item_describe, i.owner_id, " +
            "i.item_type, lh.current_price, lh.bid_time, lh.bid_owner_id FROM lot_history lh join " +
            "(select max(bid_time) max_bid_time, item_id from lot_history group by item_id) m on lh.item_id " +
            "= m.item_id and lh.bid_time = m.max_bid_time left join item i on lh.item_id = i.id";

    private static final String FIND_ALL_ITEMS_BY_STATUS_SQL = "SELECT * FROM auction.item where item_status = ?";

    private static final String REGISTER_ITEM_SQL = "INSERT INTO item " +
            "(item_name, item_describe, owner_id, item_type, start_price) VALUES (?, ?, ?, ?, ?)";


    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDaoImpl.class);

    @Override
    public boolean register(String itemName, String itemDescribe, int ownerId, int itemType, long itemPrice) {
        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_ITEM_SQL);
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, itemDescribe);
            preparedStatement.setInt(3, ownerId);
            preparedStatement.setInt(4, itemType);
            preparedStatement.setLong(5, itemPrice);
            preparedStatement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));

        }
        return true;
    }

    @Override
    public Optional<List<ItemDtoForList>> findAll(ItemStatus status) {
        if (status == null) {
            LOGGER.error("Item status equals null. Reading item failed");
            return Optional.empty();
        }
        if (ItemStatus.VALID.equals(status)) {
            return findValidItems();
        } else {
            return findItemsByStatus(status);
        }
    }

    private Optional<List<ItemDtoForList>> findItemsByStatus(ItemStatus status) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ITEMS_BY_STATUS_SQL);
            preparedStatement.setInt(1, status.getInt());
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<ItemDtoForList> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(this.readItemsByStatus(resultSet));
            }
            return Optional.of(list);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    private ItemDtoForList readItemsByStatus(ResultSet resultSet) throws SQLException {
        return new ItemDtoForList(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                ItemType.of(resultSet.getString(5)),
                resultSet.getBigDecimal(6),
                0,
                0);
    }

    private Optional<List<ItemDtoForList>> findValidItems() {
        List<ItemDtoForList> list = new ArrayList<>();

        try(final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final Statement statement = connection.createStatement();
            statement.execute(FIND_ALL_VALID_ITEMS_SQL);
            final ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                list.add(this.readValidItem(resultSet));
            }
            return Optional.of(list);
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    private ItemDtoForList readValidItem(ResultSet resultSet) throws SQLException {
        return new ItemDtoForList(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                ItemType.of(resultSet.getString(5)),
                resultSet.getBigDecimal(6),
                resultSet.getLong(7),
                resultSet.getInt(8));
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
