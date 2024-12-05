package com.solvd.transaction;

public class Client extends Person{
    private Integer loyaltyCardNumber;

    public Client() {
    }

    public Client(String name, String city, String street, String streetNumber, Integer loyaltyCardNumber) {
        super(name, city, street, streetNumber);
        this.loyaltyCardNumber = loyaltyCardNumber;
    }

    public Integer getLoyaltyCardNumber() {
        return loyaltyCardNumber;
    }

    public void setLoyaltyCardNumber(Integer loyaltyCardNumber) {
        this.loyaltyCardNumber = loyaltyCardNumber;
    }
}
