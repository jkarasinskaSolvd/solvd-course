package org.solvd.storage;

import org.solvd.transaction.Cashier;
import org.solvd.transaction.Register;

import java.util.ArrayList;
import java.util.List;

public class Supermarket {
    private String name;
    private String city;
    private String street;
    private String streetNumber;
    private Warehouse warehouse;
    private List<StoragePlace> storagePlaceList;
    private List<Register> registerList;
    private List<Cashier> cashierList;

    public Supermarket() {
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
}
