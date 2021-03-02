package com.epam.jwd.web.service;

import com.epam.jwd.web.model.ItemDtoForList;
import com.epam.jwd.web.model.ItemStatus;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<List<ItemDtoForList>> findAll(ItemStatus status);

    Optional<ItemDtoForList> register(String itemName, String itemDescribe, Object ownerId, String itemType,
                                      String itemPrice);


}
