package org.solvd.transaction;

public class Cashier {
    private String cashierName;
    private Integer cashierId;
    private String city;
    private String street;
    private String streetNumber;
    private Double salary;
    private String bankAccount;

    public Cashier() {
    }

    public Cashier(String cashierName, Integer cashierId, String city, String street, String streetNumber,
                   Double salary, String bankAccount) {
        this.cashierName = cashierName;
        this.cashierId = cashierId;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.salary = salary;
        this.bankAccount = bankAccount;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public Integer getCashierId() {
        return cashierId;
    }

    public void setCashierId(Integer cashierId) {
        this.cashierId = cashierId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
