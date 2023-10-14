package com.myjavagame.exception;

public class InvalidGameException extends Exception {
    public InvalidGameException(String message) {
        super(message); // Call the constructor of the parent class (Exception) with the message
    }
}
