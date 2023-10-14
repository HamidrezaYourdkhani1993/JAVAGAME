package com.myjavagame.exception;

public class InvalidParamException extends Exception {
    public InvalidParamException(String message) {
        super(message); // Call the constructor of the parent class (Exception) with the message
    }
}
