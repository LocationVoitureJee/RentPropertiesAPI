package com.rentpropertiesapi.exception;

public class NotFoundPropertyTypeException extends RuntimeException {
    public NotFoundPropertyTypeException(String message) {
        super(message);
    }
}
