package com.solvd.airport.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ticketClass")
@XmlType(propOrder = {"id", "name"})
public class TicketClass {
    private Long id;
    private String name;

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

    @Override
    public String toString() {
        return "TicketClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
