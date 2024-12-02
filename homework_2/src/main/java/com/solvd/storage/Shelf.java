package com.solvd.storage;

import com.solvd.product.Category;
import com.solvd.product.StorageMethod;


public class Shelf extends StoragePlace{

    public Shelf() {
        super();
        storageMethod = StorageMethod.SHELF;
    }

    public Shelf(String name, Category category){
        super(name, category);
        storageMethod = StorageMethod.SHELF;
    }
}
