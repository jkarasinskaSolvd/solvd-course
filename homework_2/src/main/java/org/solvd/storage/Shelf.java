package org.solvd.storage;

import org.solvd.product.Category;
import org.solvd.product.StorageMethod;


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
