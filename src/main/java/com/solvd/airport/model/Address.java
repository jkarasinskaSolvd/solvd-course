package com.solvd.airport.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "address")
@XmlType(propOrder = { "id", "city", "postCode", "street", "streetNumber", "apartmentNumber","countryId" })
@JsonRootName(value = "Address")
@JsonPropertyOrder( { "id", "city", "postCode", "street", "streetNumber", "apartmentNumber","countryId" })
public class Address {
    private Long id;
    private String city;
    private String postCode;
    private String street;
    private String streetNumber;
    private String apartmentNumber;
    private Long countryId;

    public Address() {
    }

    public Address(Long id, String city, String postCode, String street, String streetNumber, String apartmentNumber, Long countryId) {
        this.id = id;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.countryId = countryId;
    }

    @JsonGetter
    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    @JsonGetter
    public String getCity() {
        return city;
    }

    @XmlElement(name = "city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonGetter
    public String getPostCode() {
        return postCode;
    }

    @XmlElement(name = "postCode")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @JsonGetter
    public String getStreet() {
        return street;
    }

    @XmlElement(name = "street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonGetter
    public String getStreetNumber() {
        return streetNumber;
    }

    @XmlElement(name = "streetNumber")
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @JsonGetter
    public String getApartmentNumber() {
        return apartmentNumber;
    }

    @XmlElement(name = "apartmentNumber")
    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @JsonGetter
    public Long getCountryId() {
        return countryId;
    }

    @XmlElement(name = "countryId")
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
