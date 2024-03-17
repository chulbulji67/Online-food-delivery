package com.restaurant.controller;

import com.restaurant.model.Address;
import com.restaurant.service.address.AddressService;
import com.restaurant.service.address.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/address")
public class AddressController {

    @Autowired
    AddressServiceImpl addressService;

    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody Address address){
        return ResponseEntity.status(201).body(addressService.addNewAddress(address));
    }

    @GetMapping
    public ResponseEntity<?> getAllAddress(){
        return ResponseEntity.status(200).body(addressService.getAllAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable long id){
        return ResponseEntity.status(200).body(addressService.getAddressByAddressId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable long id, @RequestBody Address address){
        return ResponseEntity.status(200).body(addressService.updateAddressByAddressId(id, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable long id){
        return ResponseEntity.status(200).body(addressService.deleteAddressByAddressId(id));
    }
}
