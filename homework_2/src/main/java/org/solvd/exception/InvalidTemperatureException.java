package org.solvd.exception;

public class InvalidTemperatureException extends IllegalArgumentException {
    public InvalidTemperatureException() {
        super("Invalid temperature");
    }
}
