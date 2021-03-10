package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.LotDao;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.model.LotDto;
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

public enum LotDaoImpl implements LotDao {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(LotDaoImpl.class);

    private static final List<Subscriber> subscribers = new ArrayList<>();

    private static final String FIND_ALL_LOTS_SQL = "SELECT lh.id, i.id, i.item_name, i.item_describe, i.owner_id, " +
            "i.item_type, lh.current_price, lh.bid_time, lh.bid_owner_id FROM lot_history lh join (select item_id, " +
            "max(id) max_id from lot_history group by item_id) m on lh.item_id = m.item_id and lh.id = m.max_id " +
            "left join item i on lh.item_id = i.id where i.item_status = 1";

    private static final String FIND_LOT_BY_ITEM_ID_SQL = "SELECT lh.id, i.id, i.item_name, i.item_describe, i.owner_id, " +
            "i.item_type, lh.current_price, lh.bid_time, lh.bid_owner_id FROM lot_history lh join (select item_id, " +
            "max(id) max_id from lot_history group by item_id) m on lh.item_id = m.item_id and lh.id = m.max_id " +
            "left join item i on lh.item_id = i.id where i.item_status = 1 and i.id = ?";

    private final static String INSERT_ITEM_INTO_LOT_HISTORY_SQL = "INSERT INTO lot_history " +
            "(item_id, bid_time, bid_owner_id, current_price) VALUES (?, ?, ?, ?)";


    private static final String UPDATE_LOT_HISTORY_SQL = "INSERT INTO lot_history (item_id, bid_time, bid_owner_id, " +
            "current_price) VALUES (?, ?, ?, ?)";


    @Override
    public Optional<List<LotDto>> findAll() {
        List<LotDto> list = new ArrayList<>();

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final Statement statement = connection.createStatement();
            statement.execute(FIND_ALL_LOTS_SQL);
            final ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                list.add(readLot(resultSet));
            }
            return Optional.of(list);
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<LotDto> findLotByItemId(long id) {

        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(FIND_LOT_BY_ITEM_ID_SQL);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final LotDto lotDto = readLot(resultSet);
                return Optional.of(lotDto);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
        return Optional.empty();
    }

    private LotDto readLot(ResultSet resultSet) throws SQLException {
        return new LotDto(resultSet.getLong(1),
                resultSet.getLong(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5),
                ItemType.of(resultSet.getString(6)),
                resultSet.getBigDecimal(7),
                resultSet.getLong(8),
                resultSet.getInt(9));
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
            updateCash(itemId);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void insertItemIntoLotHistory(Item item) {
        try (final Connection connection = ConnectionPool.INSTANCE.retrieveConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_INTO_LOT_HISTORY_SQL);
            preparedStatement.setLong(1, item.getId());
            preparedStatement.setLong(2, item.getTime());
            preparedStatement.setInt(3, item.getOwner());
            preparedStatement.setBigDecimal(4, item.getPrice());
            preparedStatement.executeUpdate();
            updateCash(item.getId());
            LOGGER.info("Item was successfully inserted into lot_history table");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(Arrays.toString(e.getStackTrace()));
        }
    }

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
