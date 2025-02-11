package com.solvd.airport.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.airport.xml.jaxb.JAXBLocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;
@XmlRootElement(name = "flight")
@XmlType(propOrder = { "id", "startTime", "landingTime", "planeId", "startAirportId", "destinationAirportId",
        "firstPilotId","secondPilotId" })
@JsonRootName(value = "Flight")
@JsonPropertyOrder( { "id", "startTime", "landingTime", "planeId", "startAirportId", "destinationAirportId",
        "firstPilotId","secondPilotId" })
public class Flight {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime landingTime;
    private Long planeId;
    private Long startAirportId;
    private Long destinationAirportId;
    private Long firstPilotId;
    private Long secondPilotId;


    public Flight() {
    }

    public Flight(Long id, LocalDateTime startTime, LocalDateTime landingTime, Long planeId, Long startAirportId,
                  Long destinationAirportId, Long firstPilotId, Long secondPilotId) {
        this.id = id;
        this.startTime = startTime;
        this.landingTime = landingTime;
        this.planeId = planeId;
        this.startAirportId = startAirportId;
        this.destinationAirportId = destinationAirportId;
        this.firstPilotId = firstPilotId;
        this.secondPilotId = secondPilotId;
    }

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    @XmlJavaTypeAdapter(JAXBLocalDateTimeAdapter.class)
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @XmlElement(name = "startTime")
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @XmlJavaTypeAdapter(JAXBLocalDateTimeAdapter.class)
    public LocalDateTime getLandingTime() {
        return landingTime;
    }

    @XmlElement(name = "landingTime")
    public void setLandingTime(LocalDateTime landingTime) {
        this.landingTime = landingTime;
    }

    public Long getPlaneId() {
        return planeId;
    }

    @XmlElement(name = "planeId")
    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public Long getStartAirportId() {
        return startAirportId;
    }

    @XmlElement(name = "startAirportId")
    public void setStartAirportId(Long startAirportId) {
        this.startAirportId = startAirportId;
    }

    public Long getDestinationAirportId() {
        return destinationAirportId;
    }

    @XmlElement(name = "destinationAirportId")
    public void setDestinationAirportId(Long destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
    }

    public Long getFirstPilotId() {
        return firstPilotId;
    }

    @XmlElement(name = "FirstPilotId")
    public void setFirstPilotId(Long firstPilotId) {
        this.firstPilotId = firstPilotId;
    }

    public Long getSecondPilotId() {
        return secondPilotId;
    }

    @XmlElement(name = "secondPilotId")
    public void setSecondPilotId(Long secondPilotId) {
        this.secondPilotId = secondPilotId;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", landingTime=" + landingTime +
                ", planeId=" + planeId +
                ", startAirportId=" + startAirportId +
                ", destinationAirportId=" + destinationAirportId +
                ", firstPilotId=" + firstPilotId +
                ", secondPilotId=" + secondPilotId +
                '}';
    }
}
