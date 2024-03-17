package com.restaurant.controller;

import com.restaurant.model.Item;
import com.restaurant.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Create operation
    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        return ResponseEntity.status(201).body(itemService.addItemInMenu(item));
    }

    // Read operation
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(itemService.getItemDetailsById(id));
    }

    // Update operation
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        return ResponseEntity.ok(itemService.updateItemByItemId(id, itemDetails));
    }

    // Delete operation
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
//        itemService.del(id);
//        return ResponseEntity.ok().build();
//    }

    // List operation
    @GetMapping
    public ResponseEntity<?> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItemDetails());
    }

}
