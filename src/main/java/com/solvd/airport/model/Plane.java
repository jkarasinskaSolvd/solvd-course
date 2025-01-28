package com.solvd.airport.model;

public class Plane {
    private Long id;
    private String registration;
    private Integer numberOfSeats;
    private Integer productionYear;
    private Long planeTypeId;
    private Long hangarId;
    private Long airlaneId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Long getPlaneTypeId() {
        return planeTypeId;
    }

    public void setPlaneTypeId(Long planeTypeId) {
        this.planeTypeId = planeTypeId;
    }

    public Long getHangarId() {
        return hangarId;
    }

    public void setHangarId(Long hangarId) {
        this.hangarId = hangarId;
    }

    public Long getAirlaneId() {
        return airlaneId;
    }

    public void setAirlaneId(Long airlaneId) {
        this.airlaneId = airlaneId;
    }
}
