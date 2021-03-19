package com.epam.jwd.service.impl;

import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.model.Item;
import com.epam.jwd.web.model.ItemFactory;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.ItemType;
import com.epam.jwd.web.service.impl.ItemServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ItemDaoImpl.class})
public class ItemServiceImplTest {

    private static ItemDao mockItemDaoImpl;
    private static final Item TEST_ITEM =
            ItemFactory.INSTANCE.createItem(1, "test Item", "test description", 1,
            ItemType.REVERSE, BigDecimal.ONE, ItemStatus.DELETED, GregorianCalendar.getInstance().getTimeInMillis());

    @BeforeClass
    public static void init() {
        mockItemDaoImpl = mock(ItemDaoImpl.class);
        Whitebox.setInternalState(ItemDaoImpl.class, "INSTANCE", mockItemDaoImpl);
    }

    @Test
    public void findAll_shouldReturnListOfItems_always() {

        when(mockItemDaoImpl.findItemsByStatus(ItemStatus.BLOCKED)).thenReturn(Optional.of(new ArrayList<>()));

        assertTrue(ItemServiceImpl.INSTANCE.findAll(ItemStatus.BLOCKED).isPresent());
    }

    @Test
    public void register_shouldRegisterItem_always() {

        when(mockItemDaoImpl.register("Item test", "test Description", 1, 1, 10))
                .thenReturn(true);

        assertTrue(ItemServiceImpl.INSTANCE.register("Item test", "test Description", new Integer("1"), "1", "10"));
    }

    @Test
    public void update_shouldUpdateItems_always() {

        when(mockItemDaoImpl.update(TEST_ITEM)).thenReturn(true);

        assertTrue(ItemServiceImpl.INSTANCE.update(TEST_ITEM));
    }

    @Test
    public void complete_shouldCompleteItem_always() {

        when(mockItemDaoImpl.complete(1)).thenReturn(true);

        assertTrue(ItemServiceImpl.INSTANCE.complete(1));
    }

    @Test
    public void findItemsByUserId_shouldReturnNotEmptyOptionalList_always() {

        when(mockItemDaoImpl.findItemsByUserId(1)).thenReturn(Optional.of(new ArrayList<>()));

        assertTrue(ItemServiceImpl.INSTANCE.findItemsByUserId(1).isPresent());
    }

    @Test
    public void findItemById_shouldReturnNotEmptyOptional_always() {

        when(mockItemDaoImpl.findItemById(1)).thenReturn(Optional.of(TEST_ITEM));

        assertTrue(ItemServiceImpl.INSTANCE.findItemById(1).isPresent());
    }
}
