package org.solvd.transaction;

import java.util.List;

public class PhysicalRegister extends Register {
    private Cashier cashier;

    public PhysicalRegister() {
        super();
        cashier = null;
        paymentMethodList = List.of(PaymentMethod.CARD,PaymentMethod.CASH);
    }

    public PhysicalRegister(Integer id, Cashier cashier) {
        super(id);
        this.cashier = cashier;
        paymentMethodList = List.of(PaymentMethod.CARD,PaymentMethod.CASH);
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }
}
