package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.LotDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * An object that implements this interface should check and evaluate
 * input data and send request to a {@link com.epam.jwd.web.dao.LotDao} object.
 * Then if needed it should do some logic with response data.
 *
 * @author Ilia Eriomkin
 */
public interface LotService {

    /**
     * Connect with the {@link com.epam.jwd.web.dao.LotDao} object.
     * Find all {@link com.epam.jwd.web.model.LotDto} objects.
     *
     * @return all {@link com.epam.jwd.web.model.LotDto} objects.
     */
    Optional<List<LotDto>> findAll();

    /**
     * Connect with the {@link com.epam.jwd.web.dao.LotDao} object and
     * return {@link com.epam.jwd.web.model.LotDto} object if <tt>id</tt> is valid.
     *
     * @param id id of the certain lot.
     * @return {@link java.util.Optional} with certain lot or
     * {@link Optional#empty()} if <tt>id</tt> is not valid.
     */
    Optional<LotDto> findLotByItemId(long id);

    /**
     * Connect with the {@link com.epam.jwd.web.dao.LotDao} object and update lot data.
     *
     * @param itemId id of the lot item.
     * @param endTime end time of the lot after doing bid.
     * @param bidOwnerId id of the bid owner.
     * @param currentPrice new price of the lot.
     * @return true if database was successfully updated.
     */
    boolean doBid(long itemId, long endTime, int bidOwnerId, BigDecimal currentPrice);

    /**
     * Connect with the {@link com.epam.jwd.web.dao.LotDao} object and insert item into lot_history table.
     *
     * @param item item that will be added into lot_history table.
     * @return true if action was successful.
     */
    boolean insertItemIntoLotHistory(Item item);

    /**
     * Connect with the {@link com.epam.jwd.web.service.UserService} and {@link com.epam.jwd.web.service.ItemService}
     * objects and call <tt>complete()</tt> methods.
     *
     * @param lot lot that will be completed.
     * @return true if completion was successful.
     */
    boolean complete(LotDto lot);

    /**
     * <li>If someone did bid on this lot and lot type was {@link com.epam.jwd.web.model.ItemType#STRAIGHT}
     * then return money to bid owner.</li>
     * <li>Block lot item</li>
     * @param lot lot that will be blocked.
     * @return true if action was successful.
     * @see com.epam.jwd.web.model.ItemType
     */
    boolean block(LotDto lot);
}
