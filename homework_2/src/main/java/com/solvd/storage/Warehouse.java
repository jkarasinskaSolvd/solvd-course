package com.solvd.storage;

import com.solvd.Summarizable;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Summarizable {
    private List<StoragePlace> places;

    public Warehouse() {
        places = new ArrayList<>();
    }

    public Warehouse(List<StoragePlace> places) {
        this.places = places;
    }

    public List<StoragePlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<StoragePlace> places) {
        this.places = places;
    }

    public void unpackStoragePlace(StoragePlace originalPlace, StoragePlace placeOfDestination) {
        placeOfDestination.products.addAll(originalPlace.products);
        originalPlace.products.clear();
    }

    @Override
    public void summarize() {
        System.out.print("Summary of warehouse.\n");
        for(StoragePlace storagePlace : places) {
            storagePlace.summarize();
        }
        System.out.print("End of warehouse summary\n\n");
    }
}
