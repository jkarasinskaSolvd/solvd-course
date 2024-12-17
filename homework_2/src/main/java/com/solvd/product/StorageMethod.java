package com.solvd.product;

public enum StorageMethod {
    FRIDGE("Fridge", true),
    REFRIGERATOR("Refrigerator", true),
    SHELF("Shelf", false),;

    private final String name;
    private final Boolean hasColdTemperature;

    StorageMethod(String name, Boolean hasColdTemperature) {
        this.name = name;
        this.hasColdTemperature = hasColdTemperature;
    }

    public Boolean getHasColdTemperature() {
        return hasColdTemperature;
    }

    public String getName() {
        return name;
    }

    public Double electricityRate(){
        return switch(this){
            case SHELF -> 0.5;
            case REFRIGERATOR -> 2.0;
            case FRIDGE -> 4.0;
        };
    }

    static {
        System.out.println("Initializing Storage Method");
    }
}
