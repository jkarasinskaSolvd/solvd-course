package com.solvd.transaction;

public enum LoyaltyCardDiscountType {
    NO_DISCOUNT("No discount",0,0),
    BRONZE("Bronze",1,10),
    SILVER("Silver",2,20),
    GOLD("Gold",3,30),
    PLATINUM("Platinum",4,40),;

    private final String name;
    private final Integer level;
    private final Integer transactionsNeeded;

    LoyaltyCardDiscountType(String name, Integer level, Integer transactionsNeeded) {
        this.name = name;
        this.level = level;
        this.transactionsNeeded = transactionsNeeded;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getTransactionsNeeded() {
        return transactionsNeeded;
    }

    public LoyaltyCardDiscountType getNextDiscount(Integer transactionsAmount){
        if(transactionsAmount > 40){
            return LoyaltyCardDiscountType.PLATINUM;
        }
        else if(transactionsAmount > 30){
            return LoyaltyCardDiscountType.GOLD;
        }
        else if(transactionsAmount > 20){
            return LoyaltyCardDiscountType.SILVER;
        }
        else if(transactionsAmount > 10){
            return LoyaltyCardDiscountType.BRONZE;
        }
        return LoyaltyCardDiscountType.NO_DISCOUNT;
    }

    public Double calculateDiscountModifier(){
        return switch(this){
            case NO_DISCOUNT -> 1.0;
            case BRONZE -> 0.95;
            case SILVER -> 0.9;
            case GOLD -> 0.85;
            case PLATINUM -> 0.75;
        };
    }
}
