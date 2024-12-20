package com.solvd.supermarket.exception;

public class InvalidPaymentMethodException extends IllegalArgumentException{
    public InvalidPaymentMethodException(){
        super("Invalid payment method");
    }
}
