package com.restaurant.exception.menuexception;

public class MenuNotFoundException extends RuntimeException {
    public MenuNotFoundException(String s) {
        super(s);
    }
}
