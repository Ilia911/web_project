package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemFactory;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public class ItemDaoImplTest {

    private static final ItemDao ITEM_DAO = ItemDaoImpl.INSTANCE;
    private static long temporaryItemId;
    private static final long EXISTED_ITEM_ID = 12;
    private static final int EXISTED_ITEM_OWNER_ID = 1;
    private static final int TEMPORARY_ITEM_OWNER_ID = 2;

    @BeforeClass
    public static void createConnection() throws SQLException {
        ConnectionPool.INSTANCE.init();
        final Optional<List<Item>> optionalItems = ITEM_DAO.findItemsByUserId(TEMPORARY_ITEM_OWNER_ID);
        optionalItems.ifPresent(items -> temporaryItemId = items.get(0).getId());
    }

    @Test
    public void register_shouldSaveItemInDatabase_always() {
        boolean actual = ITEM_DAO.register("Item", "test example",
                TEMPORARY_ITEM_OWNER_ID, 1, 10);
        Assert.assertTrue(actual);
    }

    @Test
    public void findItemsByStatus_shouldReturnListOfBlockedItems_always() {
        final Optional<List<Item>> itemsByStatus = ITEM_DAO.findItemsByStatus(ItemStatus.BLOCKED);
        Assert.assertTrue(itemsByStatus.isPresent());
    }

    @Test
    public void findItemsByUserId_shouldReturnListOfItems_always() {
        final Optional<List<Item>> optionalItems = ITEM_DAO.findItemsByUserId(TEMPORARY_ITEM_OWNER_ID);
        optionalItems.ifPresent(items -> temporaryItemId = items.get(0).getId());
        Assert.assertTrue(optionalItems.isPresent());
    }

    @Test
    public void findItemById_shouldReturnItem_always() {
        final Optional<Item> itemById = ITEM_DAO.findItemById(EXISTED_ITEM_ID);
        Assert.assertTrue(itemById.isPresent());
    }

    @Test
    public void update_shouldUpdateItem_ifItemWithSuchIdExists() {
        final Item item = ItemFactory.INSTANCE.createItem(
                EXISTED_ITEM_ID, "name after test", "describe after test", EXISTED_ITEM_OWNER_ID,
                ItemType.REVERSE, BigDecimal.ONE, ItemStatus.VALID, GregorianCalendar.getInstance().getTimeInMillis());
        Assert.assertTrue(ITEM_DAO.update(item));
    }

    @Test
    public void complete_shouldUpdateItem_ifItemWithSuchIdExists() {
        Assert.assertTrue(ITEM_DAO.complete(EXISTED_ITEM_ID));
    }

    @Test
    public void removeItemById_shouldRemoveItem_ifSuchExists() {
        Assert.assertTrue(ITEM_DAO.removeItemById(temporaryItemId));
    }
}
