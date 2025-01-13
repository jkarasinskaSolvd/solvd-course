package com.solvd.supermarket.exception;

public class InvalidTemperatureException extends IllegalArgumentException {
    public InvalidTemperatureException() {
        super("Invalid temperature");
    }
}
