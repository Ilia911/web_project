package com.epam.jwd.web.cash;

import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.service.LotService;
import com.epam.jwd.web.service.impl.LotServiceImpl;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class LotManager extends Thread {

    private static final LotCash LOT_CASH = LotCash.INSTANCE;
    private static final LotService LOT_SERVICE = LotServiceImpl.INSTANCE;

    @Override
    public void run() {

        while (true) {
            final List<LotDto> lotDtoList = LOT_CASH.getLots().stream().collect(Collectors.toList());
            final long currentTime = GregorianCalendar.getInstance().getTimeInMillis();
            for (LotDto lot : lotDtoList) {
                if (currentTime > lot.getEndTime()) {
                    LOT_SERVICE.complete(lot);
                }
            }
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
