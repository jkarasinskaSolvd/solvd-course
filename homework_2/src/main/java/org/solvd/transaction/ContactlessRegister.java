package org.solvd.transaction;

import java.util.List;

public class ContactlessRegister extends Register {
    public ContactlessRegister() {
        super();
        paymentMethodList = List.of(PaymentMethod.CARD);
    }

    public ContactlessRegister(Integer id) {
        super(id);
        paymentMethodList = List.of(PaymentMethod.CARD);
    }
}
