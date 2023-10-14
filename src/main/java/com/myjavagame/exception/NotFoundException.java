package com.myjavagame.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message); // Call the constructor of the parent class (Exception) with the message
    }
}
