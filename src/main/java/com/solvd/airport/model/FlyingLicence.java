package com.solvd.airport.model;

import com.solvd.airport.xml.jaxb.JAXBLocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlRootElement(name = "flyingLicence")
@XmlType(propOrder = { "id", "licenceCode", "issueDate", "expirationDate", "pilotId"})
public class FlyingLicence {
    private Long id;
    private String licenceCode;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private Long pilotId;

    public Long getId() {
        return id;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenceCode() {
        return licenceCode;
    }

    @XmlElement(name = "licenceCode")
    public void setLicenceCode(String licenceCode) {
        this.licenceCode = licenceCode;
    }

    @XmlJavaTypeAdapter(JAXBLocalDateAdapter.class)
    public LocalDate getIssueDate() {
        return issueDate;
    }

    @XmlElement(name = "issueDate")
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @XmlJavaTypeAdapter(JAXBLocalDateAdapter.class)
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @XmlElement(name = "expirationDate")
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getPilotId() {
        return pilotId;
    }

    @XmlElement(name = "pilotId")
    public void setPilotId(Long pilotId) {
        this.pilotId = pilotId;
    }

    @Override
    public String toString() {
        return "FlyingLicence{" +
                "id=" + id +
                ", licenceCode='" + licenceCode + '\'' +
                ", issueDate=" + issueDate +
                ", expirationDate=" + expirationDate +
                ", pilotId=" + pilotId +
                '}';
    }
}
