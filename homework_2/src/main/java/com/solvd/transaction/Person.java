package com.solvd.transaction;

import com.solvd.Adressable;

public abstract class Person implements Adressable {
    protected String name;
    protected String city;
    protected String street;
    protected String streetNumber;

    public Person() {
    }

    public Person(String name, String city, String street, String streetNumber) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    @Override
    public String printAddress() {
        return "city: " + city + ", street: " + street + ", streetNumber: " + streetNumber;
    }
}
