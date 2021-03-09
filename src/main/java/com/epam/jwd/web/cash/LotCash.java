package com.epam.jwd.web.cash;

import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.observer.Subscriber;
import com.epam.jwd.web.service.LotService;
import com.epam.jwd.web.service.impl.LotServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public enum LotCash implements Subscriber<Long> {
    INSTANCE;

    private static final LotService LOT_SERVICE = LotServiceImpl.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(LotCash.class);

    private List<LotDto> lots = new LinkedList<>();

    public void init() {

        final Optional<List<LotDto>> optionalLotDto = LOT_SERVICE.findAll();
        optionalLotDto.ifPresent(lotDtos -> lots = lotDtos);
        LOGGER.info("Lot cash was successfully initialized");
    }

    public List<LotDto> getLots() {
        return lots;
    }

    public Optional<LotDto> getLot(long itemId) {
        for (LotDto lot : lots) {
            if (lot.getItemId() == itemId) {
                return Optional.of(lot);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Long itemId) {

        final Optional<LotDto> optionalUpdatedLot = LOT_SERVICE.findLotById(itemId);
        if (!optionalUpdatedLot.isPresent()) {
            removeLot(itemId);
            return;
        }

        for (LotDto lot : lots) {
            if (lot.getItemId() == itemId) {
                updateLots(lot, optionalUpdatedLot.get());
                return;
            }
        }

        lots.add(optionalUpdatedLot.get());

        LOGGER.info("Lot cash was successfully updated");
    }

    private void updateLots(LotDto originLot, LotDto updatedLot) {
        originLot.setId(updatedLot.getId());
        originLot.setBidOwnerId(updatedLot.getBidOwnerId());
        originLot.setEndTime(updatedLot.getEndTime());
        originLot.setPrice(updatedLot.getPrice());
    }

    private void removeLot(Long itemId) {
        for (LotDto lot : lots) {
            if (lot.getItemId() == itemId) {
                lots.remove(lot);
                break;
            }
        }
    }
}
