package com.restaurant.service.item;

import com.restaurant.dto.ItemDto;
import com.restaurant.exception.categoryexception.CategoryNotFoundException;
import com.restaurant.exception.itemexception.ItemNotFoundException;
import com.restaurant.exception.menuexception.MenuNotFoundException;
import com.restaurant.model.Category;
import com.restaurant.model.Item;
import com.restaurant.model.Menu;
import com.restaurant.repository.CategoryRepository;
import com.restaurant.repository.ItemRepository;
import com.restaurant.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public ItemDto addItemInMenu(Item item) {
        //check if menu exist in which you want to add item

        Menu menu = menuRepository.findById(item.getMenu().getId()).orElseThrow(() -> new MenuNotFoundException("Menu Not found in which You want to add Item"));
        Category category = categoryRepository.findById(item.getCategory().getId()).orElseThrow(() -> new CategoryNotFoundException("Category Not found in which You want to add Item"));
        item.setMenu(menu);
        item.setCategory(category);
        return mapItemToItemDto(itemRepository.save(item));
    }

    @Override
    public ItemDto getItemDetailsById(long id) {
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("Item Not found with the given Id"));
        return mapItemToItemDto(item);
    }

    @Override
    public List<ItemDto> getAllItemDetails() {
        return itemRepository.findAll().stream().map(this::mapItemToItemDto).toList();
    }

    @Override
    public ItemDto updateItemByItemId(long id, Item item) {
        Item existingItem = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("Item Not found with the given Id"));
        if(item.getName() != null){
            existingItem.setName(item.getName());
        }

        if(item.getDescription() != null){
            existingItem.setDescription(item.getDescription());
        }
        if(item.getPrice() != 0){
            existingItem.setPrice(item.getPrice());
        }

        return mapItemToItemDto(itemRepository.save(existingItem));
    }

    @Override
    public String DeleteItemById(long id) {
        itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException("Item Not found with the given Id"));
        itemRepository.deleteById(id);
        return "Item Deleted successfully";

    }

    ItemDto mapItemToItemDto(Item item){
        return ItemDto.builder()
                .name(item.getName())
                .id(item.getId())
                .price(item.getPrice())
                .categoryId(item.getCategory().getId())
                .categoryName(item.getCategory().getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .categoryName(item.getCategory().getName())
                .build();
    }
}
