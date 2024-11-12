package org.solvd.product;

import java.util.Objects;

public abstract class ProductType {
    protected Double pricePerUnit;

    public ProductType() {
    }

    public ProductType(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public abstract Double calculatePrice();

    @Override
    public String toString() {
        return "ProductType{" +
                getClass().getSimpleName() +
                ", price=" + pricePerUnit
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(getClass() != o.getClass()) return false;
        if(hashCode() != o.hashCode()) return false;
        ProductType productType = (ProductType) o;
        if(!Objects.equals(this.pricePerUnit, productType.pricePerUnit)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return pricePerUnit.hashCode();
    }
}
