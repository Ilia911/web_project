package com.epam.jwd.web.service;

import com.epam.jwd.web.model.ItemDto;
import com.epam.jwd.web.model.ItemType;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<List<ItemDto>> findAll();

    Optional<ItemDto> register(String itemName, String itemDescribe, String itemType, String itemPrice, String minBid,
                               long time, Object ownerId);


}
