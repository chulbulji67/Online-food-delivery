package com.restaurant.exception.userexception;

public class UserAlreadyExist extends RuntimeException {
    public UserAlreadyExist(String s) {
        super(s);
    }
}
