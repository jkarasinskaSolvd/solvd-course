package org.solvd.storage;

import org.solvd.Localizable;
import org.solvd.Summarizable;
import org.solvd.transaction.Cashier;
import org.solvd.transaction.Register;

import java.util.ArrayList;
import java.util.List;

public class Supermarket implements Summarizable {
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

    static{
        brandName = "Supermarket";
    }

    public Supermarket() {
        numberOfSupermarkets++;
        storagePlaceList = new ArrayList<StoragePlace>();
        registerList = new ArrayList<Register>();
        cashierList = new ArrayList<Cashier>();
    }

    public Supermarket(String name, String city, String street, String streetNumber, Warehouse warehouse) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.warehouse = warehouse;
        storagePlaceList = new ArrayList<StoragePlace>();
        registerList = new ArrayList<Register>();
        cashierList = new ArrayList<Cashier>();
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

    public static int getNumberOfSupermarkets() {
        return numberOfSupermarkets;
    }

    public int getSupermarketId() {
        return supermarketId;
    }

    @Override
    public void summarize() {
        List<Localizable> locationList = new ArrayList<>();
        locationList.addAll(storagePlaceList);
        locationList.addAll(registerList);
        System.out.printf("Summary of: " + this.name + "\n\n" + "Locations: \n");
        for(Localizable loc : locationList){
            System.out.printf(loc.returnLocation());
        }

        System.out.printf("\nStorage Places: \n");
        for(StoragePlace storagePlace : storagePlaceList) {
            storagePlace.summarize();
        }
        warehouse.summarize();
        System.out.printf("End of summary\n\n");
    }
}
