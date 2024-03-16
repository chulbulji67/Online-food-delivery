package com.restaurant.exception;

public class BadCredentialsExceptions extends RuntimeException{
    public BadCredentialsExceptions(String s){
        super(s);
    }
}
