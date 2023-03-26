package com.example.kafkaconsumer.exception;

public class CustomValidationException extends Exception {
    public CustomValidationException(String message) {
        super(message);
    }
}
