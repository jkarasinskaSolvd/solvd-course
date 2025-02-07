package com.solvd.airport.model;


import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "airline")
@XmlType(propOrder = {"id", "name","code","addressId"})
public class Airport {
    private Long id;
    private String name;
    private String code;
    private Long addressId;

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
