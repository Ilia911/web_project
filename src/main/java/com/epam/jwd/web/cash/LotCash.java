package com.epam.jwd.web.cash;

import com.epam.jwd.web.dao.ItemDao;
import com.epam.jwd.web.dao.impl.ItemDaoImpl;
import com.epam.jwd.web.model.ItemStatus;
import com.epam.jwd.web.model.LotDto;
import com.epam.jwd.web.observer.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public enum LotCash implements Subscriber<LotDto> {
    INSTANCE;

    private static final ItemDao ITEM_DAO = ItemDaoImpl.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger(LotCash.class);

    private List<LotDto> lots = new LinkedList<>();

    public void init() {
        final Optional<List<LotDto>> optionalLotDto = ITEM_DAO.findAll(ItemStatus.VALID);
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
    public void update() {

        final Optional<List<LotDto>> optionalLotDto = ITEM_DAO.findAll(ItemStatus.VALID);
        optionalLotDto.ifPresent(lotDtos -> lots = lotDtos);
        LOGGER.info("Lot cash was successfully updated");

    }
}
