package org.solvd.storage;

import org.solvd.StoragePlaceSummarizable;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements StoragePlaceSummarizable {
    private List<StoragePlace> places;

    public Warehouse() {
        places = new ArrayList<StoragePlace>();
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
        System.out.printf("Summary of warehouse.\n");
        for(StoragePlace storagePlace : places) {
            storagePlace.summarize();
        }
        System.out.printf("End of warehouse summary\n\n");
    }
}
