package com.solvd.airport.model;

import java.time.LocalDateTime;

public class Flight {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime landingTime;
    private Long planeId;
    private Long startAirportId;
    private Long destinationAirportId;
    private Long firstPilotId;
    private Long secondPilotId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(LocalDateTime landingTime) {
        this.landingTime = landingTime;
    }

    public Long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }

    public Long getStartAirportId() {
        return startAirportId;
    }

    public void setStartAirportId(Long startAirportId) {
        this.startAirportId = startAirportId;
    }

    public Long getDestinationAirportId() {
        return destinationAirportId;
    }

    public void setDestinationAirportId(Long destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
    }

    public Long getFirstPilotId() {
        return firstPilotId;
    }

    public void setFirstPilotId(Long firstPilotId) {
        this.firstPilotId = firstPilotId;
    }

    public Long getSecondPilotId() {
        return secondPilotId;
    }

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
