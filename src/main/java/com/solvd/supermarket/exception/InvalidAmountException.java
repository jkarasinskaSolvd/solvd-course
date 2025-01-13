package com.solvd.supermarket.exception;

public class InvalidAmountException extends IllegalArgumentException{
    public InvalidAmountException(String productName){
        super("Invalid amount of: " + productName);
    }
}
