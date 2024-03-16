package com.restaurant.exception.cartitemexception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String s) {
        super(s);
    }
}
