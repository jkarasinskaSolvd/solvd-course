package com.solvd.storage;

import com.solvd.Localizable;
import com.solvd.Summarizable;
import com.solvd.exception.InvalidAmountException;
import com.solvd.exception.InvalidCategoryException;
import com.solvd.exception.InvalidStorageMethodException;
import com.solvd.product.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class StoragePlace implements Localizable, Summarizable {
    protected String name;
    protected Category category;
    protected List<Product> products;
    protected StorageMethod storageMethod;
    protected String location;

    public StoragePlace() {
        products = new ArrayList<>();
    }

    public StoragePlace(String name, Category category) {
        this.name = name;
        this.category = category;
        products = new ArrayList<>();
        this.location = null;
    }

    public void addProduct(Product product) throws InvalidCategoryException, InvalidStorageMethodException{
        if (category != product.getCategory()){
            throw new InvalidCategoryException(product.getName(), product.getCategory(), category);
        }
        if(storageMethod != product.getStorageMethod()) {
            throw new InvalidStorageMethodException(product.getName(), product.getStorageMethod(),storageMethod);
        }
        products.add(product);
    }

    public void addListOfProducts(List<Product> listOfProducts) {
        for (Product product : listOfProducts) {
            addProduct(product);
        }
    }
// remove product but partially
    public void removeProduct(Product product) throws InvalidAmountException{
        products.stream()
                .filter(product1 -> product1.equals(product))
                .findFirst()
                .ifPresent(product1 -> {
                    if(product1.getType() instanceof WeightedProduct){
                        WeightedProduct weightedProduct1 = (WeightedProduct) product1.getType();
                        WeightedProduct weightedProduct = (WeightedProduct) product.getType();
                        Double weightLeft = weightedProduct1.getWeightInKilograms()
                                            - weightedProduct.getWeightInKilograms();
                        if(weightLeft == 0){
                            products.remove(product);
                        }if(weightLeft < 0){
                            throw new InvalidAmountException(product.getName());
                        }else{
                            weightedProduct1.setWeightInKilograms(weightLeft);
                            product1.setType(weightedProduct1);
                        }
                    } else if (product1.getType() instanceof SingleProduct) {
                        SingleProduct singleProduct1 = (SingleProduct) product1.getType();
                        SingleProduct singleProduct = (SingleProduct) product.getType();
                        Integer itemsLeft = singleProduct1.getAmount() - singleProduct.getAmount();
                        if(itemsLeft == 0){
                            products.remove(product);
                        }if(itemsLeft < 0){
                            throw new InvalidAmountException(product.getName());
                        }else{
                            singleProduct1.setAmount(itemsLeft);
                            product1.setType(singleProduct1);
                        }
                    }
                });
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "StoragePlace{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", products=" + products +
                ", storageMethod=" + storageMethod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(getClass() != o.getClass()) return false;
        if(hashCode() != o.hashCode()) return false;
        StoragePlace storagePlace = (StoragePlace) o;
        if(!Objects.equals(this.name, storagePlace.name)) return false;
        if(this.category != storagePlace.category) return false;
        if(this.products != storagePlace.products) return false;
        if(this.storageMethod != storagePlace.storageMethod) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + category.hashCode() + products.hashCode() + storageMethod.hashCode();
    }

    @Override
    public String returnLocation(){
        return this.getClass().getSimpleName() + " " + this.name + " Location: " + location + "\n";
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void summarize() {
        System.out.printf("Summary of " + this.name +"\n");
        for(Product product : products){
            System.out.printf(product.toString() + "\n");
        }
        System.out.println("Number of products: " + products.size());
    }
}
