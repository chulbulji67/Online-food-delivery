package com.restaurant.controller;

import com.restaurant.model.DeliveryPerson;
import com.restaurant.service.deliveryperson.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryPersonController {
    @Autowired
    DeliveryPersonService deliveryPersonService;

    @PostMapping
    public ResponseEntity<?> createDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        return ResponseEntity.ok(deliveryPersonService.addDeliveryPerson(deliveryPerson));
    }

    // Read operation
    @GetMapping("/{id}")
    public ResponseEntity<?> getDeliveryPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryPersonService.getDeliveryPersonById(id));
    }

    // Update operation
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDeliveryPerson(@PathVariable Long id, @RequestBody DeliveryPerson deliveryPerson) {

        return ResponseEntity.ok(deliveryPersonService.updateAvailability(id, deliveryPerson));
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeliveryPerson(@PathVariable Long id) {
        return ResponseEntity.ok( deliveryPersonService.deleteDeliveryPerson(id));
    }

    // List operation
    @GetMapping("/{availability}/{location}")
    public ResponseEntity<?> getAllDeliveryPersons(@PathVariable boolean availability,@PathVariable String location) {
        return ResponseEntity.ok(deliveryPersonService.getAllDeliveryPersons(availability, location));
    }

}
