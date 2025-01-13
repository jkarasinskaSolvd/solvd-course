package com.solvd.supermarket.storage;

import com.solvd.supermarket.*;
import com.solvd.supermarket.lambda.FilterAndMapStream;
import com.solvd.supermarket.lambda.MapAndReduceStream;
import com.solvd.supermarket.transaction.Cashier;
import com.solvd.supermarket.transaction.Client;
import com.solvd.supermarket.transaction.Register;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Supermarket implements Summarizable, Adressable, Cleanable, Emptyable, Runnable {
    private static String brandName;
    private String name;
    private String city;
    private String street;
    private String streetNumber;
    private Warehouse warehouse;
    private static int numberOfSupermarkets = 0;
    private final int supermarketId = ++numberOfSupermarkets;
    private List<StoragePlace> storagePlaceList;
    private List<Register> registerList;
    private List<Cashier> cashierList;
    private Boolean isClean = false;

    static {
        brandName = "Supermarket";
    }

    public Supermarket() {
        numberOfSupermarkets++;
        storagePlaceList = new ArrayList<>();
        registerList = new ArrayList<>();
        cashierList = new ArrayList<>();
    }

    public Supermarket(String name, String city, String street, String streetNumber, Warehouse warehouse) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.warehouse = warehouse;
        storagePlaceList = new ArrayList<>();
        registerList = new ArrayList<>();
        cashierList = new ArrayList<>();
        numberOfSupermarkets++;
    }

    public static String getBrandName() {
        return brandName;
    }

    public static void setBrandName(String brandName) {
        Supermarket.brandName = brandName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<StoragePlace> getStoragePlaceList() {
        return storagePlaceList;
    }

    public void setStoragePlaceList(List<StoragePlace> storagePlaceList) {
        this.storagePlaceList = storagePlaceList;
    }

    public List<Register> getRegisterList() {
        return registerList;
    }

    public void setRegisterList(List<Register> registerList) {
        this.registerList = registerList;
    }

    public List<Cashier> getCashierList() {
        return cashierList;
    }

    public void setCashierList(List<Cashier> cashierList) {
        this.cashierList = cashierList;
    }

    public Boolean getClean() {
        return isClean;
    }

    public void setClean(Boolean clean) {
        isClean = clean;
    }

    public static int getNumberOfSupermarkets() {
        return numberOfSupermarkets;
    }

    public int getSupermarketId() {
        return supermarketId;
    }

    public void updateCard(Client client){
        if(!client.getLoyaltyCard().getDiscountType().equals(client.nextCard())){
            client.getLoyaltyCard().setDiscountType(client.nextCard());
        }
    }

    @Override
    public void summarize() {
        new Thread(this).start();
    }

    @Override
    public String printAddress() {
        return "city: " + city + ", street: " + street + ", streetNumber: " + streetNumber;
    }

    @Override
    public void clean(Cashier cashier) {
        if (!isClean) {
            isClean = true;
            System.out.println("Supermarket cleaned by: " + cashier.getCashierId());
        } else {
            System.out.println("Supermarket is clean, do something else");
        }
    }

    @Override
    public void empty() {
        warehouse.empty();
        for (StoragePlace place : storagePlaceList) {
            place.empty();
        }
        System.out.println("Supermarket " + name + " is empty");
    }

    public String displayPlacesToClean() {
        FilterAndMapStream<StoragePlace, String> filterAndMapStream =
                (stream, predicate, function) -> stream
                        .filter(predicate)
                        .map(function);

        Stream<String> placesToClean =
                filterAndMapStream.filterAndMap(storagePlaceList.stream(), StoragePlace::getNotClean, StoragePlace::getName);
        StringBuilder summary = new StringBuilder();
        summary.append("Places to clean:\n");
        placesToClean.forEach(
                place -> summary.append(place).append("\n")
        );
        return summary.toString();

    }

    public Double calculateElectricityCost() {
        MapAndReduceStream<StoragePlace, Double> mapAndReduceStream =
                (stream, function, reducer) ->
                        stream
                                .map(function)
                                .reduce(0.0, reducer);
        Double supermarketElectricityCost = mapAndReduceStream.mapAndReduce(
                storagePlaceList.stream(),
                StoragePlace::calculateElectricityCost,
                Double::sum
                );
        return supermarketElectricityCost + warehouse.calculateElectricityCost();
    }

    @Override
    public void run() {
        System.out.println("Thread started");
        List<Localizable> locationList = new ArrayList<>();
        locationList.addAll(storagePlaceList);
        locationList.addAll(registerList);
        System.out.printf("Summary of: " + this.name + "\n\n" + "Locations: \n");
        for (Localizable loc : locationList) {
            System.out.printf(loc.returnLocation());
        }

        System.out.print("\nStorage Places: \n");
        for (StoragePlace storagePlace : storagePlaceList) {
            storagePlace.summarize();
        }
        warehouse.summarize();

        System.out.println("Monthly electricity cost: "+calculateElectricityCost());
        System.out.print("End of summary\n\n");
    }
}
