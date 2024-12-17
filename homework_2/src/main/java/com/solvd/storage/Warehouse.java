package com.solvd.storage;

import com.solvd.Cleanable;
import com.solvd.Emptyable;
import com.solvd.Summarizable;
import com.solvd.exception.CapacityExceededException;
import com.solvd.lambda.MapAndReduceStream;
import com.solvd.transaction.Cashier;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Summarizable, Cleanable, Emptyable {
    private List<StoragePlace> places;
    private Boolean isClean;

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

    public Boolean getClean() {
        return isClean;
    }

    public void setClean(Boolean clean) {
        isClean = clean;
    }

    public void unpackStoragePlace(StoragePlace originalPlace, StoragePlace placeOfDestination) {
        if(originalPlace.storageSize.getCapacity()<=placeOfDestination.storageSize.getCapacity()
                && placeOfDestination.getProducts().size() +
                originalPlace.getProducts().size() < placeOfDestination.storageSize.getCapacity()) {
            placeOfDestination.products.addAll(originalPlace.products);
            originalPlace.products.clear();
            placeOfDestination.setNotClean();
        }
        else throw new CapacityExceededException("Could not unpack as capacity would be exceeded!");

    }

    public Double calculateElectricityCost(){
        MapAndReduceStream<StoragePlace, Double> mapAndReduceStream =
                (stream, function, reducer) ->
                        stream
                                .map(function)
                                .reduce(0.0, reducer);
        return mapAndReduceStream.mapAndReduce(
                places.stream(),
                StoragePlace::calculateElectricityCost,
                Double::sum
        );
    }

    @Override
    public void summarize() {
        System.out.print("Summary of warehouse.\n");
        for(StoragePlace storagePlace : places) {
            storagePlace.summarize();
        }
        System.out.print("End of warehouse summary\n\n");
    }

    @Override
    public void clean(Cashier cashier) {
        if(!isClean){
            isClean = true;
            System.out.println("Warehouse cleaned by: " + cashier.getCashierId());
        }else {
            System.out.println("Warehouse is clean, do something else");
        }
    }

    @Override
    public void empty() {
        for(StoragePlace place : places){
            place.empty();
        }
        System.out.println("Warehouse empty");
    }
}
