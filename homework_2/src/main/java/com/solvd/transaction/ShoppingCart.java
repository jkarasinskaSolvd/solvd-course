package com.solvd.transaction;

import com.solvd.CustomLinkedList;
import com.solvd.exception.InvalidAmountException;
import com.solvd.lambda.FilterAndCompareStream;
import com.solvd.lambda.FilterAndCountStream;
import com.solvd.lambda.MapAndCheckIfAnyMatchStream;
import com.solvd.product.*;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class ShoppingCart {
    private CustomLinkedList<Product> products;
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


    public ShoppingCart() {
        products = new CustomLinkedList<>(); //ArrayList is implementation of List interface
    }

    public ShoppingCart(CustomLinkedList<Product> products) {
        this.products = products;
    }

    public CustomLinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(CustomLinkedList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if(Objects.nonNull(product.getType())) {
            if(product.getType() instanceof SingleProduct){
                if(isEqualToZero.test(((SingleProduct) product.getType()).getAmount())
                        || isSmallerThanZero.test(((SingleProduct) product.getType()).getAmount())) {
                    throw new InvalidAmountException(product.getName());
                }
            }
            if(product.getType() instanceof WeightedProduct){
                if(isEqualToZero.test(((WeightedProduct) product.getType()).getWeightInKilograms())
                        || isSmallerThanZero.test(((WeightedProduct) product.getType()).getWeightInKilograms())) {
                    throw new InvalidAmountException(product.getName());
                }
            }
            products.add(product);
        }

    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public final Double totalPrice() {
        Double total = 0.0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getType().calculatePrice();
        }
        return total;
    }

    public void printProducts() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).toString() + "\n");
        }
    }

    public Optional<Product> getMostValuableProductByType(Class<? extends ProductType> type) {
        Predicate<Product> predicateForProductType = (productLambda) -> Objects.nonNull(productLambda.getType()) && productLambda.getType()
                .getClass().equals(type);
        FilterAndCompareStream<Product> filterAndCompareCollection =
                (stream, predicate, comparator) ->
                        stream.filter(predicate).max(comparator);

        return filterAndCompareCollection.filterAndCompare(
                products.stream(),
                predicateForProductType,
                Product::compareTo);
    }

    public boolean checkIfCategoryInTheCart(Category category) {
        Predicate<Category> predicateForCategory = (categoryLambda) -> categoryLambda.equals(category);
        MapAndCheckIfAnyMatchStream<Product, Category> mapAndCheckIfAnyMatchStream =
                (stream, function, predicate) ->
                        stream.map(function).anyMatch(predicate);

        return mapAndCheckIfAnyMatchStream.mapAndCheckIfAnyMatch(
                products.stream(),
                Product::getCategory,
                predicateForCategory
        );
    }

    public Long countProductsByCategoryParameter(Predicate<Product> predicate) {
        FilterAndCountStream<Product> filterAndCountStream =
                (stream, predicateLambda) -> stream.filter(predicateLambda).count();

        return filterAndCountStream.filterAndCount(products.stream(), predicate);
    }
}
