package com.solvd.airport.model;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "airport")
@XmlType(propOrder = {"id", "name","code","addressId"})
@JsonRootName("Airport")
@JsonPropertyOrder({"id", "name","code","addressId"})
public class Airport {
    private Long id;
    private String name;
    private String code;
    private Long addressId;

    public Airport() {
    }

    public Airport(Long id, String name, String code, Long addressId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.addressId = addressId;
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

    public String getCode() {
        return code;
    }

    @XmlElement(name = "code")
    public void setCode(String code) {
        this.code = code;
    }

    public Long getAddressId() {
        return addressId;
    }

    @XmlElement(name = "addressId")
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}
