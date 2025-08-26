package com.shapesapi.exception;

public class ShapeNotFoundException extends RuntimeException {
    public ShapeNotFoundException(String message) {
        super(message);
    }
}
