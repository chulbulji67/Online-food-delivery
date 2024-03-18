package com.restaurant.controller;

import com.restaurant.model.Cart;
import com.restaurant.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<?> createCart(@RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.createCartForAUser(cart), HttpStatus.CREATED);
    }

    // Read operation
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);

    }

    // Update operation
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Cart> updateCart(@PathVariable("id") Long id, @RequestBody Cart cart) {
//        Cart updatedCart = cartService.(id, cart);
//
//            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
//
//    }

    // Delete operation
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id) {
//        boolean deleted = cartService.(id);
//        if (deleted) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}
