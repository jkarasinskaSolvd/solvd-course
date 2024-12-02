package com.solvd.exception;

public class InvalidTemperatureException extends IllegalArgumentException {
    public InvalidTemperatureException() {
        super("Invalid temperature");
    }
}
