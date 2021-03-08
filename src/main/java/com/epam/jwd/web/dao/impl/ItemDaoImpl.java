package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.observer.Subscriber;
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

public enum ItemDaoImpl implements ItemDao {
    INSTANCE;

    private static final List<Subscriber> subscribers = new ArrayList<>();

    private static final String FIND_ALL_VALID_ITEMS_SQL = "SELECT lh.id, i.id, i.item_name, i.item_describe, i.owner_id, " +
            "i.item_type, lh.current_price, lh.bid_time, lh.bid_owner_id FROM lot_history lh join (select item_id, " +
            "max(id) max_id from lot_history group by item_id) m on lh.item_id = m.item_id and lh.id = m.max_id " +
            "left join item i on lh.item_id = i.id where i.item_status = 1";

    private static final String FIND_ALL_ITEMS_BY_STATUS_SQL = "SELECT * FROM auction.item where item_status = ?";

    private static final String REGISTER_ITEM_SQL = "INSERT INTO item " +
            "(item_name, item_describe, owner_id, item_type, start_price) VALUES (?, ?, ?, ?, ?)";

    private static final String UNBLOCK_ITEM_SQL = "UPDATE item SET item_status = ? WHERE (id = ?)";

    private final static String INSERT_ITEM_INTO_LOT_HISTORY_SQL = "INSERT INTO lot_history " +
            "(item_id, bid_time, bid_owner_id, current_price) VALUES (?, ?, ?, ?)";

    private static final String FIND_VALID_ITEM_BY_ID_SQL = "SELECT lh.id, i.id, i.item_name, i.item_describe, i.owner_id, " +
            "i.item_type, lh.current_price, lh.bid_time, lh.bid_owner_id FROM lot_history lh join (select item_id, " +
            "max(id) max_id from lot_history group by item_id) m on lh.item_id = m.item_id and lh.id = m.max_id " +
            "left join item i on lh.item_id = i.id where i.item_status = 1 and i.id = ?";

    private static final String UPDATE_LOT_HISTORY_SQL = "INSERT INTO lot_history (item_id, bid_time, bid_owner_id, " +
            "current_price) VALUES (?, ?, ?, ?)";

    private static final String COMPLETE_LOT_SQL = "UPDATE item SET item_status = '3' WHERE (`id` = ?)";

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
            LOGGER.info("Item '" + itemName + "' was successfully registered");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return true;
    }

    @Override
    public Optional<List<LotDto>> findAll(ItemStatus status) {
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
    public Optional<LotDto> findValidItemById(long id) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_VALID_ITEM_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            LotDto lotDto;
            if (resultSet.next()) {
                lotDto = readValidItem(resultSet);
                return Optional.of(lotDto);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    private Optional<List<LotDto>> findItemsByStatus(ItemStatus status) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ITEMS_BY_STATUS_SQL);
            preparedStatement.setInt(1, status.getInt());
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<LotDto> list = new ArrayList<>();
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

    private LotDto readItemsByStatus(ResultSet resultSet) throws SQLException {
        return new LotDto(0,
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                ItemType.of(resultSet.getString(5)),
                resultSet.getBigDecimal(6),
                0,
                0);
    }

    private Optional<List<LotDto>> findValidItems() {
        List<LotDto> list = new ArrayList<>();

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

    private LotDto readValidItem(ResultSet resultSet) throws SQLException {
        return new LotDto(resultSet.getLong(1),
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5),
                ItemType.of(resultSet.getString(6)),
                resultSet.getBigDecimal(7),
                resultSet.getLong(8),
                resultSet.getInt(9));
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
        insertItemIntoLotHistory(item);
        updateItemStatusInItemTable(item);
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
            updateCash();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void complete(LotDto lotDto) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(COMPLETE_LOT_SQL);
            preparedStatement.setLong(1, lotDto.getItemId());
            preparedStatement.executeUpdate();
            LOGGER.info("Lot # " + lotDto.getItemId() + " was successfully completed");
            updateCash();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void subscribe(Subscriber<LotDto> subscriber) {
        subscribers.add(subscriber);
    }

    private void updateItemStatusInItemTable(Item item) {
        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(UNBLOCK_ITEM_SQL);
            preparedStatement.setInt(1, item.getStatus().getInt());
            preparedStatement.setLong(2, item.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Item was successfully unblocked");
            updateCash();
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
    private void updateCash() {

        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

}
