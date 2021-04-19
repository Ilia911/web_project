package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.observer.Publisher;

import java.util.List;
import java.util.Optional;

/**
 * An object that implements this interface should connect with database using connection from
 * {@link com.epam.jwd.web.connection.ConnectionPool}, do CRUD actions and notify listeners.
 *
 * @author Ilia Eriomkin
 */
public interface ItemDao extends Publisher<Long> {

    /**
     * Connect with database and register new item.
     *
     * @param itemName     item name.
     * @param itemDescribe item description.
     * @param ownerId      item owner id.
     * @param itemType     item type.
     * @param itemPrice    start item price.
     * @return true if item was successfully registered.
     */
    boolean register(String itemName, String itemDescribe, int ownerId, int itemType, long itemPrice);

    /**
     * Connect with database and return all items with specific {@link ItemStatus}.
     *
     * @param status item status by which search items.
     * @return {@link Optional} with list of items or {@link Optional#empty()} if items with such status not exist.
     */
    Optional<List<Item>> findItemsByStatus(ItemStatus status);

    /**
     * Connect with database, update item and notify listeners.
     *
     * @param item updated item.
     * @return true if updating was successful.
     */
    boolean update(Item item);

    /**
     * Connect with database and return all items by owner id.
     *
     * @param userId owner id
     * @return {@link Optional} with list of items or {@link Optional#empty()} if there were Exception with database.
     */
    Optional<List<Item>> findItemsByUserId(int userId);

    /**
     * Connect with database and return item by id.
     *
     * @param id item id.
     * @return {@link Optional} with item by id.
     */
    Optional<Item> findItemById(long id);

    /**
     * Connect with database, change item status and notify listeners.
     *
     * @param id item id.
     * @return true if the action was successful.
     */
    boolean complete(long id);

    /**
     * Connect with database, delete item by id and notify listeners.
     *
     * @param id item id.
     * @return true if the action was successful.
     */
    boolean removeItemById(long id);
}
