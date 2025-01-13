package com.solvd.supermarket.storage;

import com.solvd.supermarket.product.Category;
import com.solvd.supermarket.product.StorageMethod;


public class Shelf extends StoragePlace{

    public Shelf() {
        super();
        storageMethod = StorageMethod.SHELF;
    }

    public Shelf(String name, Category category, StorageSize storageSize){
        super(name, category, storageSize);
        storageMethod = StorageMethod.SHELF;
    }
}
