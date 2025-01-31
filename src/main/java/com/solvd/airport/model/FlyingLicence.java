package com.solvd.airport.model;

import java.time.LocalDate;

public class FlyingLicence {
    private Long id;
    private String licenceCode;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private Long pilotId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenceCode() {
        return licenceCode;
    }

    public void setLicenceCode(String licenceCode) {
        this.licenceCode = licenceCode;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getPilotId() {
        return pilotId;
    }

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
