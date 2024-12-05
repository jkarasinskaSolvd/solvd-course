package com.solvd.transaction;

public class Cashier extends Person{
    private Integer cashierId;
    private Double salary;
    private String bankAccount;

    public Cashier() {
    }

    public Cashier(String cashierName, Integer cashierId, String city, String street, String streetNumber,
                   Double salary, String bankAccount) {
        super(cashierName, city, street, streetNumber);
        this.cashierId = cashierId;
        this.salary = salary;
        this.bankAccount = bankAccount;
    }

    public String getCashierName() {
        return name;
    }

    public void setCashierName(String cashierName) {
        this.name = cashierName;
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
