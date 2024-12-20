package com.solvd.supermarket.transaction;

import com.solvd.supermarket.Localizable;

import java.util.ArrayList;
import java.util.List;

public abstract class Register implements Localizable {
    protected Integer id;
    protected List<Transaction> transactionList;
    protected List<PaymentMethod> paymentMethodList;
    protected String location;

    public Register() {
        transactionList = new ArrayList<>();
        paymentMethodList = new ArrayList<>();
    }

    public Register(Integer id) {
        this.id = id;
        transactionList = new ArrayList<>();
        paymentMethodList = new ArrayList<>();
        this.location = null;
    }

    @Override
    public String returnLocation(){
        return this.getClass().getSimpleName() + " id: " + this.id + " Location: " + location + "\n";
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }
}
