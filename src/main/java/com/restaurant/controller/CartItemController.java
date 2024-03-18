package com.restaurant.controller;

import com.restaurant.model.CartItem;
import com.restaurant.service.cartitem.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/cart-item")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<?> createCartItem(@RequestBody CartItem cartItem) {
        return new ResponseEntity<>( cartItemService.addCartItem(cartItem), HttpStatus.CREATED);
    }

    // Get a cart item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartItemById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cartItemService.getAllCartItem(), HttpStatus.OK);
    }



    // Delete a cart item
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cartItemService.deleteCartItemById(id), HttpStatus.valueOf(200));
    }
}
