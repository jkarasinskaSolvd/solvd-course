package com.solvd.supermarket.product;

public enum Category {

    DIARY("Diary", true, true, true),
    GREENGROCERIES("Groceries", true, true, false),
    MEAT("Meat", true, true, true),
    SWEETS("Sweets", true, true, false),
    HYGIENE("Hygene", false, true, true),
    BOOKS("Books", false, false, false),
    TOYS("Toys", false, false, false);

    private final String name;
    private final Boolean edible;
    private final Boolean consumable;
    private final Boolean needsColdTemperature;

    Category(String name, Boolean edible, Boolean consumable, Boolean needsColdTemperature) {
        this.name = name;
        this.edible = edible;
        this.consumable = consumable;
        this.needsColdTemperature = needsColdTemperature;
    }

    public String getName() {
        return name;
    }

    public Boolean getEdible() {
        return edible;
    }

    public Boolean getConsumable() {
        return consumable;
    }

    public Boolean getNeedsColdTemperature() {
        return needsColdTemperature;
    }

    public Boolean isRequiredToBeInTheCoolingUnit(){
        return getEdible() && getConsumable() && getNeedsColdTemperature();
    }
    static {
        System.out.println("Initializing Category");
    }


}

