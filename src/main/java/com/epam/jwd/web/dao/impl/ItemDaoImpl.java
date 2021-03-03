package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemDtoForList;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
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
            "= m.item_id and lh.bid_time = m.max_bid_time left join item i on lh.item_id = i.id where i.item_status = 1";

    private static final String FIND_ALL_ITEMS_BY_STATUS_SQL = "SELECT * FROM auction.item where item_status = ?";

    private static final String REGISTER_ITEM_SQL = "INSERT INTO item " +
            "(item_name, item_describe, owner_id, item_type, start_price) VALUES (?, ?, ?, ?, ?)";

    private static final String UNBLOCK_ITEM_SQL = "UPDATE item SET item_status = ? WHERE (id = ?)";

    private final static String INSERT_ITEM_INTO_LOT_HISTORY_SQL = "INSERT INTO lot_history " +
            "(item_id, bid_time, bid_owner_id, current_price) VALUES (?, ?, ?, ?)";

    private static final String FIND_VALID_ITEM_BY_ID_SQL = "SELECT i.id, i.item_name, i.item_describe, i.owner_id, " +
            "i.item_type, lh.current_price, lh.bid_time, lh.bid_owner_id FROM lot_history lh join " +
            "(select max(bid_time) max_bid_time, item_id from lot_history group by item_id) m on lh.item_id " +
            "= m.item_id and lh.bid_time = m.max_bid_time left join item i on lh.item_id = i.id " +
            "where i.item_status = 1 and i.id = ?";

    private static final String UPDATE_LOT_HISTORY_SQL = "INSERT INTO lot_history (item_id, bid_time, bid_owner_id, current_price) VALUES (?, ?, ?, ?)";


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

    @Override
    public Optional<ItemDtoForList> findValidItemById(long id) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_VALID_ITEM_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            ItemDtoForList itemDtoForList = null;
            if (resultSet.next()) {
               itemDtoForList = readValidItem(resultSet);
                return Optional.of(itemDtoForList);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
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

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
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
    public Optional<Item> update(Item entity) {
        return Optional.empty();
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public void unblock(Item item) {
        updateItemStatusInItemTable(item);
        insertItemIntoLotHistory(item);
    }

    @Override
    public void doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOT_HISTORY_SQL);
            preparedStatement.setLong(1, itemId);
            preparedStatement.setLong(2, bidTime);
            preparedStatement.setInt(3, bidOwnerId);
            preparedStatement.setBigDecimal(4, currentPrice);
            preparedStatement.executeUpdate();
            LOGGER.info("Lot history was successfully updated");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
    }

    private void updateItemStatusInItemTable(Item item) {
        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(UNBLOCK_ITEM_SQL);
            preparedStatement.setInt(1, item.getStatus().getInt());
            preparedStatement.setLong(2, item.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Item was successfully unblocked");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
    }

    private void insertItemIntoLotHistory(Item item) {
        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_INTO_LOT_HISTORY_SQL);
            preparedStatement.setLong(1, item.getId());
            preparedStatement.setLong(2, item.getTime());
            preparedStatement.setInt(3, item.getOwner());
            preparedStatement.setBigDecimal(4, item.getPrice());
            preparedStatement.executeUpdate();
            LOGGER.info("Item was successfully inserted into lot_history table");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
    }
}
