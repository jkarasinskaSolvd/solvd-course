package com.solvd.supermarket.transaction;

public enum PaymentMethod {
    CASH("Cash", false, false),
    CARD("Card", true, true),;

    private final String name;
    private final Boolean contactless;
    private final Boolean traceable;

    PaymentMethod(String name, Boolean contactless, Boolean traceable) {
        this.name = name;
        this.contactless = contactless;
        this.traceable = traceable;
    }

    public String getName() {
        return name;
    }

    public Boolean getContactless() {
        return contactless;
    }

    public Boolean getTraceable() {
        return traceable;
    }

    public Double calculateTaxModifier(){
        return switch (this){
            case CARD -> 1.05;
            case CASH -> 1.0;
        };
    }

    static {
        System.out.println("Initializing Payment Method");
    }

}