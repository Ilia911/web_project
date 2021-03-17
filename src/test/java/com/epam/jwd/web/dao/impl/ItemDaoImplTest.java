package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemFactory;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import org.junit.AfterClass;
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

    @BeforeClass
    public static void createConnection() throws SQLException {
        ConnectionPool.INSTANCE.init();
    }

    @Test
    public void register_shouldSaveItemInDatabase_always() {

        boolean actual = ITEM_DAO.register("Item", "test example", 2, 1, 10);
        Assert.assertTrue(actual);
    }

    @Test
    public void findItemsByStatus_shouldReturnListOfBlockedItems_always() {
        final Optional<List<Item>> itemsByStatus = ITEM_DAO.findItemsByStatus(ItemStatus.BLOCKED);
        Assert.assertTrue(itemsByStatus.isPresent());
    }

    @Test
    public void findItemsByUserId_shouldReturnListOfItems_always() {
        final Optional<List<Item>> itemsByUserId = ITEM_DAO.findItemsByUserId(2);
        Assert.assertTrue(itemsByUserId.isPresent());
    }

    @Test
    public void findItemById_shouldReturnItem_always() {
        final Optional<Item> itemById = ITEM_DAO.findItemById(2);
        Assert.assertTrue(itemById.isPresent());
    }

    @Test
    public void update_shouldUpdateItem_ifItemWithSuchIdExists() {
        final Item item = ItemFactory.INSTANCE.createItem(
                1, "name after test", "describe after test", 2,
                ItemType.STRAIGHT, BigDecimal.ONE, ItemStatus.VALID, GregorianCalendar.getInstance().getTimeInMillis());
        Assert.assertTrue(ITEM_DAO.update(item));
    }

    @Test
    public void complete_shouldUpdateItem_ifItemWithSuchIdExists() {
        Assert.assertTrue(ITEM_DAO.complete(1));
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        ConnectionPool.INSTANCE.destroy();
    }
}
