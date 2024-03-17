package com.restaurant.controller;

import com.restaurant.model.Order;
import com.restaurant.service.order.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;


    // Create operation
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.CreateOrder(order));
    }

    // Read operation
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
            return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllOrderOfAUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getAllOrderOfAUserById(userId));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> getAllOrderOfARestaurantById(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(orderService.getAllOrderOfARestaurantById(restaurantId));
    }

    // Update operation
    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrderById(@PathVariable Long orderId, @RequestBody Order order) {
            return ResponseEntity.ok(orderService.updateOrderById(orderId, order));

    }

    // Delete operation
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
            return ResponseEntity.ok(orderService.cancelOrderById(orderId));

    }

}
