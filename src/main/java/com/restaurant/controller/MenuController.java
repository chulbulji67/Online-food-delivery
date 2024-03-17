package com.restaurant.controller;

import com.restaurant.model.Menu;
import com.restaurant.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping
    public ResponseEntity<?> addMenu(@RequestBody Menu menu){
        return ResponseEntity.status(201).body(menuService.addMenuForRestaurant(menu));
    }
    @GetMapping
    public ResponseEntity<?> getAllMenu(){
        return ResponseEntity.status(201).body(menuService.getAllMenu());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllMenu(@PathVariable long id){
        return ResponseEntity.status(200).body(menuService.getMenuById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuById(@PathVariable long id){
        return ResponseEntity.status(200).body(menuService.deleteMenuById(id));
    }

}
