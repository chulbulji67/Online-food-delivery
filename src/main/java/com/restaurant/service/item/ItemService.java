package com.restaurant.service.item;

import com.restaurant.dto.ItemDto;
import com.restaurant.model.Item;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface ItemService {
    public ItemDto addItemInMenu(Item item);

    public ItemDto getItemDetailsById(long id);

    public List<ItemDto> getAllItemDetails();

    public ItemDto updateItemByItemId(long id, Item item);

    public String DeleteItemById(long id);
}
