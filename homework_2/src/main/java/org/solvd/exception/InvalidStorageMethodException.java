package org.solvd.exception;

import org.solvd.product.StorageMethod;

public class InvalidStorageMethodException extends IllegalArgumentException{
    public InvalidStorageMethodException(String productName, StorageMethod productStorageMethod,
                                         StorageMethod storageMethod) {
        super("Storage method: "+ storageMethod + " does not match " + productName + " storage method: "
                + productStorageMethod);
    }
}
