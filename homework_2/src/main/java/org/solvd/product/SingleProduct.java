package org.solvd.product;

public class SingleProduct extends ProductType {
    private Integer amount;

    public SingleProduct() {
        super();
    }

    public SingleProduct(Double pricePerUnit, Integer amount) {
        super(pricePerUnit);
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public Double calculatePrice() {
        return amount * pricePerUnit;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", amount=" + amount +
                '}';
    }
}
