package com.epam.jwd.web.cash;

import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.service.ItemService;
import com.epam.jwd.web.service.impl.ItemServiceImpl;

import java.util.GregorianCalendar;
import java.util.List;

public class LotManager extends Thread {

    private static final LotCash LOT_CASH = LotCash.INSTANCE;
    private static final ItemService ITEM_SERVICE = ItemServiceImpl.INSTANCE;

    @Override
    public void run() {
        LOT_CASH.init();

        final List<LotDto> lots = LOT_CASH.getLots();

        while (true) {
            long currentTime = GregorianCalendar.getInstance().getTimeInMillis();
            for (LotDto lot : lots) {
                if (currentTime > lot.getEndTime()) {
                    ITEM_SERVICE.complete(lot);
                }

            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
