package com.example.kafkaconsumer.exception;

public class DuplicateException extends Throwable {
    public DuplicateException(String message) {
        super(message);
    }
}
