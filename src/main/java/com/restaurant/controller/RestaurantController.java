package com.restaurant.controller;

import com.restaurant.model.Address;
import com.restaurant.model.Restaurant;
import com.restaurant.repository.AddressRepository;
import com.restaurant.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    AddressRepository addressRepository;

    @PostMapping("/restaurant")
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant){
        Address address = addressRepository.findById(restaurant.getAddress().getId()).orElse(restaurant.getAddress());
        addressRepository.save(address);
        restaurant.setAddress(address);
        return ResponseEntity.status(201).body(restaurantService.addRestaurant(restaurant));
    }

    @GetMapping("/restaurant")
    public ResponseEntity<?> getAllRestaurant(){
        return ResponseEntity.status(200).body(restaurantService.getAllRestaurant());
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable long id){
        return ResponseEntity.status(200).body(restaurantService.getRestaurantById(id));
    }

    @PutMapping("/restaurant/{id}")
    public ResponseEntity<?> updateRestaurantById(@PathVariable long id, @RequestBody Restaurant restaurant){
        return ResponseEntity.status(200).body(restaurantService.updateRestaurantById(id, restaurant));
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<?> deleteRestaurantById(@PathVariable long id){
        return ResponseEntity.status(200).body(restaurantService.deleteRestaurantById(id));
    }

}
