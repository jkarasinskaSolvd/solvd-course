package com.solvd.exception;

public class InvalidAmountException extends IllegalArgumentException{
    public InvalidAmountException(String productName){
        super("Invalid amount of: " + productName);
    }
}
