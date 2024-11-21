package org.solvd.transaction;

import org.solvd.Localizable;

import java.util.ArrayList;
import java.util.List;

public abstract class Register implements Localizable {
    protected Integer id;
    protected List<Transaction> transactionList;
    protected List<PaymentMethod> paymentMethodList;
    protected String location;

    public Register() {
        transactionList = new ArrayList<Transaction>();
        paymentMethodList = new ArrayList();
    }

    public Register(Integer id) {
        this.id = id;
        transactionList = new ArrayList<Transaction>();
        paymentMethodList = new ArrayList();
        this.location = null;
    }

    @Override
    public String printLocation(){
        return this.getClass().getSimpleName() + " id: " + this.id + " Location: " + location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }
}
