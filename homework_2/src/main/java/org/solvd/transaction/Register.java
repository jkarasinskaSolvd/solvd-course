package org.solvd.transaction;

import java.util.ArrayList;
import java.util.List;

public abstract class Register {
    protected Integer id;
    protected List<Transaction> transactionList;
    protected List<PaymentMethod> paymentMethodList;

    public Register() {
        transactionList = new ArrayList<Transaction>();
        paymentMethodList = new ArrayList();
    }

    public Register(Integer id) {
        this.id = id;
        transactionList = new ArrayList<Transaction>();
        paymentMethodList = new ArrayList();
    }
}
