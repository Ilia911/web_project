package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.observer.Publisher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * An object that implements this interface should connect with database using connection from
 * {@link com.epam.jwd.web.connection.ConnectionPool}, do CRUD actions and notify listeners.
 *
 * @author Ilia Eriomkin
 */
public interface LotDao extends Publisher<Long> {

    /**
     * Connect with database and return all lots.
     *
     * @return {@link Optional} with lot list.
     */
    Optional<List<LotDto>> findAll();

    /**
     * Connect with database and return lot.
     *
     * @param id item id.
     * @return {@link Optional} with lot or {@link Optional#empty()} if <tt>login</tt>> is not valid.
     */
    Optional<LotDto> findLotByItemId(long id);

    /**
     * Connect with database, insert new string into <tt>lot_history</tt> table and notify listeners.
     *
     * @param itemId       item id.
     * @param bidTime      the end of the lot time.
     * @param bidOwnerId   bid owner id.
     * @param currentPrice new current price for lot.
     * @return true if the action was successful.
     */
    boolean doBid(long itemId, long bidTime, int bidOwnerId, BigDecimal currentPrice);

    /**
     * Connect with database, insert new string into <tt>lot_history</tt> table and notify listeners.
     *
     * @param item new valid item.
     * @return true if the action was successful.
     */
    boolean insertItemIntoLotHistory(Item item);
}
