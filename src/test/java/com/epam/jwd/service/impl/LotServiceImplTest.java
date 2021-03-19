package com.epam.jwd.service.impl;

import com.epam.jwd.web.dao.LotDao;
import com.epam.jwd.web.dao.impl.LotDaoImpl;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemFactory;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.UserService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import com.epam.jwd.web.service.impl.LotServiceImpl;
import com.epam.jwd.web.service.impl.UserServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LotDaoImpl.class, ItemServiceImpl.class, UserServiceImpl.class})
public class LotServiceImplTest {

    private static LotDao mockLotDao;
    private static ItemService mockItemService;
    private static UserService mockUserService;

    private static final LotDto TRUE_TEST_LOT = new LotDto(1, 1, "test Item", "test description",
            1, ItemType.STRAIGHT, BigDecimal.ONE, 0, 2);
    private static final LotDto FALSE_TEST_LOT = new LotDto(1, 1, "test Item", "test description",
            1, ItemType.STRAIGHT, BigDecimal.ONE, 0, 1);
    private static final Item VALID_TEST_ITEM = ItemFactory.INSTANCE.createItem(1, "test Item",
            "test description", 1, ItemType.STRAIGHT, BigDecimal.ONE, ItemStatus.VALID, 0);
    private static final Item BLOCKED_TEST_ITEM = ItemFactory.INSTANCE.createItem(1, "test Item",
            "test description", 1, ItemType.STRAIGHT, BigDecimal.ONE, ItemStatus.BLOCKED, 0);


    @BeforeClass
    public static void init() {
        mockLotDao = mock(LotDaoImpl.class);
        Whitebox.setInternalState(LotDaoImpl.class, "INSTANCE", mockLotDao);
        mockItemService = mock(ItemServiceImpl.class);
        Whitebox.setInternalState(ItemServiceImpl.class, "INSTANCE", mockItemService);
        mockUserService = mock(UserServiceImpl.class);
        Whitebox.setInternalState(UserServiceImpl.class, "INSTANCE", mockUserService);
    }

    @Test
    public void findAll_shouldReturnOptionalListOfLots_ifSuchExist() {

        when(mockLotDao.findAll()).thenReturn(Optional.of(new ArrayList<>()));

        assertTrue(LotServiceImpl.INSTANCE.findAll().isPresent());
    }

    @Test
    public void findLotByItemId_shouldReturnOptionalLot_ifSuchExists() {

        when(mockLotDao.findLotByItemId(1)).thenReturn(Optional.of(TRUE_TEST_LOT));

        assertTrue(LotServiceImpl.INSTANCE.findLotByItemId(1).isPresent());
    }

    @Test
    public void doBid_shouldUpdateLot_ifSuchExist() {

        when(mockLotDao.doBid(1, 0, 1, BigDecimal.ONE))
                .thenReturn(true);

        assertTrue(LotServiceImpl.INSTANCE.doBid(1, 0,
                1, BigDecimal.ONE));
    }

    @Test
    public void insertItemIntoLotHistory_shouldInsertItemIntoLotHistory_always() {

        when(mockLotDao.insertItemIntoLotHistory(VALID_TEST_ITEM)).thenReturn(true);

        assertTrue(LotServiceImpl.INSTANCE.insertItemIntoLotHistory(VALID_TEST_ITEM));
    }

    @Test
    public void complete_shouldUpdateItemStatus_always() {

        when(mockItemService.complete(TRUE_TEST_LOT.getItemId())).thenReturn(true);
        when(mockUserService.complete(TRUE_TEST_LOT)).thenReturn(true);

        assertTrue(LotServiceImpl.INSTANCE.complete(TRUE_TEST_LOT));
    }

    @Test
    public void block_shouldBlockItem_whenOwnerIdEqualsBidOwnerId() {

        when(mockItemService.findItemById(FALSE_TEST_LOT.getItemId())).thenReturn(Optional.of(VALID_TEST_ITEM));
        when(mockItemService.update(BLOCKED_TEST_ITEM)).thenReturn(true);

        assertFalse(LotServiceImpl.INSTANCE.block(FALSE_TEST_LOT));
    }

    @Test
    public void block_shouldBlockItemAndReturnMoney_whenOwnerIdEqualsBidOwnerId() {

        when(mockUserService.updateAccount(TRUE_TEST_LOT.getBidOwnerId(), TRUE_TEST_LOT.getPrice().negate())).thenReturn(true);
        when(mockItemService.findItemById(TRUE_TEST_LOT.getItemId())).thenReturn(Optional.of(VALID_TEST_ITEM));
        when(mockItemService.update(BLOCKED_TEST_ITEM)).thenReturn(true);

        assertTrue(LotServiceImpl.INSTANCE.block(TRUE_TEST_LOT));
    }
}
