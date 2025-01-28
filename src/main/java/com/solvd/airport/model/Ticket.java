package com.solvd.airport.model;

public class Ticket {
    private Long id;
    private String ticketCode;
    private String seat;
    private Long flightId;
    private Long passengerId;
    private Long ticketClassId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public Long getTicketClassId() {
        return ticketClassId;
    }

    public void setTicketClassId(Long ticketClassId) {
        this.ticketClassId = ticketClassId;
    }
}
