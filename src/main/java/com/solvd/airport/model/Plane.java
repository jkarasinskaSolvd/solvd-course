package com.solvd.airport.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "plane")
@XmlType(propOrder = {"id", "registration","numberOfSeats","productionYear","planeTypeId","hangarId","airlineId"})
@JsonRootName("Plane")
@JsonPropertyOrder({"id", "registration","numberOfSeats","productionYear","planeTypeId","hangarId","airlineId"})
public class Plane {
    private Long id;
    private String registration;
    private Integer numberOfSeats;
    private Integer productionYear;
    private Long planeTypeId;
    private Long hangarId;
    private Long airlineId;

    public Plane() {
    }

    public Plane(Long id, String registration, Integer numberOfSeats, Integer productionYear, Long planeTypeId, Long hangarId, Long airlineId) {
        this.id = id;
        this.registration = registration;
        this.numberOfSeats = numberOfSeats;
        this.productionYear = productionYear;
        this.planeTypeId = planeTypeId;
        this.hangarId = hangarId;
        this.airlineId = airlineId;
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    @XmlElement(name = "registration")
    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    @XmlElement(name = "numberOfSeats")
    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    @XmlElement(name = "productionYear")
    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Long getPlaneTypeId() {
        return planeTypeId;
    }

    @XmlElement(name = "planeTypeId")
    public void setPlaneTypeId(Long planeTypeId) {
        this.planeTypeId = planeTypeId;
    }

    public Long getHangarId() {
        return hangarId;
    }

    @XmlElement(name = "hangar")
    public void setHangarId(Long hangarId) {
        this.hangarId = hangarId;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    @XmlElement(name = "airlineId")
    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", registration='" + registration + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", productionYear=" + productionYear +
                ", planeTypeId=" + planeTypeId +
                ", hangarId=" + hangarId +
                ", airlineId=" + airlineId +
                '}';
    }
}
