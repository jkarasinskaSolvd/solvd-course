package com.solvd.supermarket.exception;

public class ObjectCreationFailureException extends InstantiationException{
    public ObjectCreationFailureException(String message) {
        super(message);
    }
}
