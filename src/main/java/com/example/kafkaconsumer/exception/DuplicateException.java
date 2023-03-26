package com.example.kafkaconsumer.exception;

public class DuplicateException extends Exception {
    public DuplicateException(String message) {
        super(message);
    }
}
