package com.solvd.transaction;

public class LoyaltyCard {
    private final Integer loyaltyCardId;
    private LoyaltyCardDiscountType discountType;

    public LoyaltyCard(Integer loyaltyCardId, LoyaltyCardDiscountType discountType) {
        this.loyaltyCardId = loyaltyCardId;
        this.discountType = discountType;
    }

    public Integer getLoyaltyCardId() {
        return loyaltyCardId;
    }

    public LoyaltyCardDiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(LoyaltyCardDiscountType discountType) {
        this.discountType = discountType;
    }
}
