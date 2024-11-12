package org.solvd.product;

public class WeightedProduct extends ProductType {
    private Double weightInKilograms;

    public WeightedProduct() {
        super();
    }

    public WeightedProduct(Double pricePerUnit, Double weightInKilograms) {
        super(pricePerUnit);
        this.weightInKilograms = weightInKilograms;
    }

    public Double getWeightInKilograms() {
        return weightInKilograms;
    }

    public void setWeightInKilograms(Double weightInKilograms) {
        this.weightInKilograms = weightInKilograms;
    }

    @Override
    public Double calculatePrice() {
        return weightInKilograms * pricePerUnit;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", weight in kilograms=" + weightInKilograms +
                '}';
    }
}
