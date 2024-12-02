package com.solvd.exception;

public class InvalidPaymentMethodException extends IllegalArgumentException{
    public InvalidPaymentMethodException(){
        super("Invalid payment method");
    }
}
