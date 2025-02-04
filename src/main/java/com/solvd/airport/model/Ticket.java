package com.solvd.airport.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ticket")
@XmlType(propOrder = {"id", "ticketCode", "seat", "flightId", "passengerId", "ticketClassId"})
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

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    @XmlElement(name ="ticketCode")
    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getSeat() {
        return seat;
    }

    @XmlElement(name ="seat")
    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Long getFlightId() {
        return flightId;
    }

    @XmlElement(name ="flightId")
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    @XmlElement(name ="passengerId")
    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public Long getTicketClassId() {
        return ticketClassId;
    }

    @XmlElement(name ="ticketClassId")
    public void setTicketClassId(Long ticketClassId) {
        this.ticketClassId = ticketClassId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketCode='" + ticketCode + '\'' +
                ", seat='" + seat + '\'' +
                ", flightId=" + flightId +
                ", passengerId=" + passengerId +
                ", ticketClassId=" + ticketClassId +
                '}';
    }
}
