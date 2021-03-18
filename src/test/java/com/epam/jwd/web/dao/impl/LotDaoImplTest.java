package com.epam.jwd.web.dao.impl;

import com.epam.jwd.web.cash.LotCash;
import com.epam.jwd.web.connection.ConnectionPool;
import com.epam.jwd.web.dao.LotDao;
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

public class LotDaoImplTest {

    private static final LotDao LOT_DAO = LotDaoImpl.INSTANCE;
    private static final long EXISTED_ITEM_ID = 19;
    private static final int ITEM_OWNER_ID = 1;
    private static final int BID_OWNER_ID = 2;

    @Test
    public void findAll_shouldReturnListLotDto_always() {
        Assert.assertTrue(LOT_DAO.findAll().isPresent());
    }

    @Test
    public void findLotByItemId_shouldReturnLot_ifSuchExists() {
        Assert.assertTrue(LOT_DAO.findLotByItemId(EXISTED_ITEM_ID).isPresent());
    }

    @Test
    public void doBid_shouldUpdateLotHistory_always() {
        LOT_DAO.doBid(EXISTED_ITEM_ID, GregorianCalendar.getInstance().getTimeInMillis(), BID_OWNER_ID, BigDecimal.ONE);
    }

    @Test
    public void insertItemIntoLotHistory_shouldInsertItemIntoLotHistory_always() {
        final Item item = ItemFactory.INSTANCE.createItem(EXISTED_ITEM_ID, "Item name", "Item Describe",
                ITEM_OWNER_ID, ItemType.STRAIGHT, BigDecimal.ONE, ItemStatus.VALID,
                GregorianCalendar.getInstance().getTimeInMillis());
        Assert.assertTrue(LOT_DAO.insertItemIntoLotHistory(item));
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        ConnectionPool.INSTANCE.destroy();
    }
}
