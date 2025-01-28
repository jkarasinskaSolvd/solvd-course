package com.solvd.airport.model;

public class Airline {
    private Long id;
    private String name;
    private Long registrationCountryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRegistrationCountryId() {
        return registrationCountryId;
    }

    public void setRegistrationCountryId(Long registrationCountryId) {
        this.registrationCountryId = registrationCountryId;
    }
}
