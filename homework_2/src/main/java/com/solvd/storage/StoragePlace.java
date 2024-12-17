package com.solvd.storage;

import com.solvd.Cleanable;
import com.solvd.Emptyable;
import com.solvd.Localizable;
import com.solvd.Summarizable;
import com.solvd.exception.CapacityExceededException;
import com.solvd.exception.InvalidAmountException;
import com.solvd.exception.InvalidCategoryException;
import com.solvd.exception.InvalidStorageMethodException;
import com.solvd.product.*;
import com.solvd.transaction.Cashier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static com.solvd.Const.ELECTRICITY_RATE;

public abstract class StoragePlace implements Localizable, Summarizable, Cleanable, Emptyable {
    protected String name;
    protected Category category;
    protected List<Product> products;
    protected StorageMethod storageMethod;
    protected String location;
    protected Boolean isClean;
    protected StorageSize storageSize;

    private final Predicate<Object> isSmallerThanZero =
            (object) -> Objects.nonNull(object) &&
                    switch (object) {
                        case Integer i -> i < 0;
                        case Double d -> d < 0;
                        case Long l -> l < 0;
                        default -> false;
                    };

    private final Predicate<Object> isEqualToZero =
            (object) -> Objects.nonNull(object) &&
                    switch (object) {
                        case Integer i -> i == 0;
                        case Double d -> d == 0;
                        case Long l -> l == 0;
                        default -> false;
                    };

    private final BiConsumer<ProductType, List<Predicate<Object>>> productValidator =
            (productType, objectPredicateList) ->
            {
                switch (productType) {
                    case SingleProduct singleProduct -> {
                        if (objectPredicateList.stream().anyMatch(objectPredicate -> objectPredicate.test(singleProduct.getAmount()))) {
                            throw new RuntimeException("Invalid product amount declared");
                        }
                    }
                    case WeightedProduct weightedProduct -> {
                        if (objectPredicateList.stream().anyMatch(objectPredicate -> objectPredicate.test(weightedProduct.getWeightInKilograms()))) {
                            throw new RuntimeException("Invalid product amount declared");
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + productType);
                }
            };


    public StoragePlace() {
        products = new ArrayList<>();
    }

    public StoragePlace(String name, Category category, StorageSize storageSize) {
        this.name = name;
        this.category = category;
        this.storageSize = storageSize;
        products = new ArrayList<>();
        this.location = null;
    }

    public Boolean getClean() {
        return Objects.nonNull(isClean) && isClean;
    }

    public Boolean getNotClean() {
        return Objects.nonNull(isClean) && !isClean;
    }

    public void setNotClean(){
        isClean = false;
    }
    public String getName() {
        return name;
    }

    public void addProduct(Product product) throws InvalidCategoryException, InvalidStorageMethodException {
        try {
            productValidator.accept(product.getType(), List.of(isSmallerThanZero, isEqualToZero));
        } catch (RuntimeException e) {
            throw new InvalidAmountException(e.getMessage());
        }
        if (category != product.getCategory()) {
            throw new InvalidCategoryException(product.getName(), product.getCategory(), category);
        }
        if (storageMethod != product.getStorageMethod()) {
            throw new InvalidStorageMethodException(product.getName(), product.getStorageMethod(), storageMethod);
        }
        if(storageMethod.getHasColdTemperature() != category.isRequiredToBeInTheCoolingUnit()){
            throw new InvalidStorageMethodException(product.getName(), product.getStorageMethod(), storageMethod);
        }
        if(storageSize.getCapacity()<=products.size()){
            throw new CapacityExceededException(product);
        }
        products.add(product);
        isClean = false;
    }

    public void addListOfProducts(List<Product> listOfProducts) {
        for (Product product : listOfProducts) {
            addProduct(product);
        }
    }

    // remove product but partially
    public void removeProduct(Product product) throws InvalidAmountException {
        products.stream()
                .filter(product1 -> product1.equals(product))
                .findFirst()
                .ifPresent(product1 -> {
                    if (product1.getType() instanceof WeightedProduct) {
                        WeightedProduct weightedProduct1 = (WeightedProduct) product1.getType();
                        WeightedProduct weightedProduct = (WeightedProduct) product.getType();
                        Double weightLeft = weightedProduct1.getWeightInKilograms()
                                - weightedProduct.getWeightInKilograms();
                        if (isEqualToZero.test(weightLeft)) {
                            products.remove(product);
                        }
                        if (isSmallerThanZero.test(weightLeft)) {
                            throw new InvalidAmountException(product.getName());
                        } else {
                            weightedProduct1.setWeightInKilograms(weightLeft);
                            product1.setType(weightedProduct1);
                        }
                    } else if (product1.getType() instanceof SingleProduct) {
                        SingleProduct singleProduct1 = (SingleProduct) product1.getType();
                        SingleProduct singleProduct = (SingleProduct) product.getType();
                        Integer itemsLeft = singleProduct1.getAmount() - singleProduct.getAmount();
                        if (isEqualToZero.test(itemsLeft)) {
                            products.remove(product);
                        }
                        if (isSmallerThanZero.test(itemsLeft)) {
                            throw new InvalidAmountException(product.getName());
                        } else {
                            singleProduct1.setAmount(itemsLeft);
                            product1.setType(singleProduct1);
                        }
                    }
                });
    }

    public List<Product> getProducts() {
        return products;
    }

    public Double calculateElectricityCost(){
        return ELECTRICITY_RATE * storageSize.electricityRate() * storageMethod.electricityRate();
    }

    @Override
    public String toString() {
        return "StoragePlace{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", products=" + products +
                ", storageMethod=" + storageMethod +
                ", storageSize=" + storageSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        if (hashCode() != o.hashCode()) return false;
        StoragePlace storagePlace = (StoragePlace) o;
        if (!Objects.equals(this.name, storagePlace.name)) return false;
        if (this.category != storagePlace.category) return false;
        if (this.products != storagePlace.products) return false;
        if (this.storageMethod != storagePlace.storageMethod) return false;
        if (this.storageSize != storagePlace.storageSize) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + category.hashCode() + products.hashCode() + storageMethod.hashCode();
    }

    @Override
    public String returnLocation() {
        return this.getClass().getSimpleName() + " " + this.name + " Location: " + location + "\n";
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void summarize() {
        System.out.printf("Summary of " + this.name + "\n");
        for (Product product : products) {
            System.out.printf(product.toString() + "\n");
        }
        System.out.println("Number of products: " + products.size());
    }

    @Override
    public void clean(Cashier cashier) {
        if (!isClean) {
            isClean = true;
            System.out.println("Storage Place: " + name + " cleaned by: " + cashier.getCashierId());
        } else {
            System.out.println("This place is clean, do something else");
        }
    }

    @Override
    public void empty() {
        products.clear();
        System.out.println("Storage Place: " + name + " emptied");
    }
}
