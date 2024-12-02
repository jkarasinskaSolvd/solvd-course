package com.solvd.exception;

import com.solvd.product.Category;

public class InvalidCategoryException extends IllegalArgumentException{
    public InvalidCategoryException(String productName, Category productCategory,Category storagePlaceCategory){
        super("Category: "+ storagePlaceCategory + " does not match " + productName + " category: " + productCategory);
    }
}
