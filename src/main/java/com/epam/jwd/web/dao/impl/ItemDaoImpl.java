package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemFactory;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.observer.Subscriber;
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

public enum ItemDaoImpl implements ItemDao {
          INSTANCE;

    private static final List<Subscriber> subscribers = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDaoImpl.class);

    private static final String FIND_ALL_ITEMS_BY_STATUS_SQL = "SELECT * FROM item where item_status = ?";

    private static final String REGISTER_ITEM_SQL = "INSERT INTO item " +
            "(item_name, item_describe, owner_id, item_type, start_price) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_ITEM_SQL = "UPDATE item SET item_name = ?, item_describe = ?, start_price = ?, " +
            "item_status = ?, item_type = ? WHERE (id = ?)";

    private static final String FIND_ITEMS_BY_USER_ID_SQL = "SELECT * FROM item where owner_id = ?";

    private static final String FIND_ITEM_BY_ID_SQL = "SELECT * FROM item where id = ?";

    private static final String COMPLETE_LOT_SQL = "UPDATE item SET item_status = '3' WHERE (`id` = ?)";

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
    public Optional<List<Item>> findItemsByStatus(ItemStatus status) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ITEMS_BY_STATUS_SQL);
            preparedStatement.setInt(1, status.getInt());
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<Item> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(this.readItem(resultSet));
            }
            return Optional.of(list);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Item> update(Item item) {
        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM_SQL);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescribe());
            preparedStatement.setBigDecimal(3, item.getPrice());
            preparedStatement.setInt(4, item.getStatus().getInt());
            preparedStatement.setInt(5, item.getType().getType());
            preparedStatement.setLong(6, item.getId());
            preparedStatement.executeUpdate();
            updateCash(item.getId());
            LOGGER.info("Item was successfully updated");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Item>> findItemsByUserId(int userId) {
        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_ITEMS_BY_USER_ID_SQL);
            preparedStatement.setInt(1, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<Item> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(this.readItem(resultSet));
            }
            return Optional.of(list);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Item> findItemById(long id) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_ITEM_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(readItem(resultSet));
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    @Override
    public void complete(long id) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(COMPLETE_LOT_SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("Lot # " + id + " was successfully completed");
            updateCash(id);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
    }

    private Item readItem(ResultSet resultSet) throws SQLException {
        return ItemFactory.INSTANCE.createItem(
                resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                ItemType.of(resultSet.getString(5)),
                resultSet.getBigDecimal(6),
                ItemStatus.of(resultSet.getString(7)),
                0);
    }

@SuppressWarnings("unchecked")
    private void updateCash(long id) {

        for (Subscriber subscriber : subscribers) {
            subscriber.update(id);
        }
    }

    @Override
    public void subscribe(Subscriber<? super Long> subscriber) {
        subscribers.add(subscriber);
    }

}
