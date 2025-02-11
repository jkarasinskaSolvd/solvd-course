package com.solvd.airport.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "airline")
@XmlType(propOrder = {"id", "name","registrationCountryId"})
@JsonRootName("Airline")
@JsonPropertyOrder({"id", "name","registrationCountryId"})
public class Airline {
    private Long id;
    private String name;
    private Long registrationCountryId;

    public Airline() {
    }

    public Airline(Long id, String name, Long registrationCountryId) {
        this.id = id;
        this.name = name;
        this.registrationCountryId = registrationCountryId;
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "city")
    public void setName(String name) {
        this.name = name;
    }

    public Long getRegistrationCountryId() {
        return registrationCountryId;
    }

    @XmlElement(name = "registrationCountryId")
    public void setRegistrationCountryId(Long registrationCountryId) {
        this.registrationCountryId = registrationCountryId;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationCountryId=" + registrationCountryId +
                '}';
    }
}
