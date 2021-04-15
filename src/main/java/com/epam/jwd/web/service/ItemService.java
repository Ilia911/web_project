package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemStatus;

import java.util.List;
import java.util.Optional;

/**
 * An object that implements this interface should check and evaluate
 * input data and send request to a {@link com.epam.jwd.web.dao.ItemDao} object.
 * Then if needed it should do some logic with response data.
 *
 * @author Ilia Eriomkin
 */
public interface ItemService {

    /**
     * Connect with {@link com.epam.jwd.web.dao.ItemDao} object and call <tt>findItemsByStatus()</tt> method.
     *
     * @param status an object {@link com.epam.jwd.web.model.ItemStatus} by which will be found items.
     * @return {@link Optional<List<Item>} with list of items found by <tt>ItemStatus</tt>
     */
    Optional<List<Item>> findAll(ItemStatus status);

    /**
     * Register new item.
     * Connect with {@link com.epam.jwd.web.dao.ItemDao} object and call
     * {@link com.epam.jwd.web.dao.ItemDao#register(String, String, int, int, long)} method.
     *
     * @param itemName     item name.
     * @param itemDescribe item subscription.
     * @param ownerId      id of the item owner.
     * @param itemType     type of the item.
     * @param itemPrice    start item price.
     * @return true if registration was successful.
     */
    boolean register(String itemName, String itemDescribe, Object ownerId, String itemType,
                     String itemPrice);

    /**
     * Update item.
     * Connect with {@link com.epam.jwd.web.dao.ItemDao} object and call
     * {@link com.epam.jwd.web.dao.ItemDao#update(Item)} method.
     *
     * @param item item with updated data.
     * @return true if updating was successful.
     */
    boolean update(Item item);

    /**
     * Change item status by {@link com.epam.jwd.web.model.ItemStatus#COMPLETED}.
     * Connect with {@link com.epam.jwd.web.dao.ItemDao} object and call
     * {@link com.epam.jwd.web.dao.ItemDao#complete(long)} method.
     *
     * @param itemId id of the completed item.
     * @return true if item was successfully updated.
     */
    boolean complete(long itemId);

    /**
     * Find list of items by item owner id.
     * Connect with {@link com.epam.jwd.web.dao.ItemDao} object and call
     * {@link com.epam.jwd.web.dao.ItemDao#findItemsByUserId(int)} method.
     *
     * @param userId user id by which find list of items.
     * @return list of items.
     */
    Optional<List<Item>> findItemsByUserId(int userId);

    /**
     * Find item by item id.
     * Connect with {@link com.epam.jwd.web.dao.ItemDao} object and call
     * {@link com.epam.jwd.web.dao.ItemDao#findItemById(long)} method.
     *
     * @param id item id.
     * @return {@link Optional} with founded item if <tt>id</tt> is valid or {@link Optional#empty()} if <tt>id</tt> is not valid.
     */
    Optional<Item> findItemById(long id);
}
