package com.solvd.supermarket.exception;

import com.solvd.supermarket.product.Category;

public class InvalidCategoryException extends IllegalArgumentException{
    public InvalidCategoryException(String productName, Category productCategory,Category storagePlaceCategory){
        super("Category: "+ storagePlaceCategory + " does not match " + productName + " category: " + productCategory);
    }
}
