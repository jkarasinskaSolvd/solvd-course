package com.solvd.supermarket.transaction;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person{
    private LoyaltyCard loyaltyCard;
    private List<Transaction> transactionList;

    public Client() {
    }

    public Client(String name, String city, String street, String streetNumber, LoyaltyCard loyaltyCard) {
        super(name, city, street, streetNumber);
        this.loyaltyCard = loyaltyCard;
        this.transactionList = new ArrayList<Transaction>();
    }

    public LoyaltyCard getLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCardNumber(LoyaltyCard loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public LoyaltyCardDiscountType nextCard(){
        return loyaltyCard.getDiscountType().getNextDiscount(transactionList.size());
    }
}
