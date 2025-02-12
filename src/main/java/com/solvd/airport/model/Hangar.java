package com.solvd.airport.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "hangar")
@XmlType(propOrder = {"id", "name","capacity","airportId"})
@JsonRootName("Hangar")
@JsonPropertyOrder({"id", "name","capacity","airportId"})
public class Hangar {
    private Long id;
    private String name;
    private Integer capacity;
    private Long airportId;

    public Hangar() {
    }

    public Hangar(Long id, String name, Integer capacity, Long airportId) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.airportId = airportId;
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

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    @XmlElement(name = "capacity")
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Long getAirportId() {
        return airportId;
    }

    @XmlElement(name = "airportId")
    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    @Override
    public String toString() {
        return "Hangar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", airportId=" + airportId +
                '}';
    }
}
