package com.solvd.exception;

import com.solvd.product.Product;

public class CapacityExceededException extends RuntimeException {
    public CapacityExceededException(Product product) {
        super( "Product "+product.getName()+ " could not be added as it would exceed the capacity");
    }

    public CapacityExceededException(String message) {
        super(message);
    }
}
