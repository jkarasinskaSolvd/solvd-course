package com.solvd.transaction;

import com.solvd.CustomLinkedList;
import com.solvd.exception.InvalidAmountException;
import com.solvd.lambda.IsEqualToZero;
import com.solvd.lambda.IsSmallerThanZero;
import com.solvd.product.Product;
import com.solvd.product.SingleProduct;

public class ShoppingCart {
    private CustomLinkedList<Product> products;
    private IsEqualToZero<Object> isEqualToZero;
    private IsSmallerThanZero<Object> isSmallerThanZero;

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
        if(product.getType() instanceof SingleProduct){
            if(isEqualToZero.test(((SingleProduct) product.getType()).getAmount())
            || isSmallerThanZero.test(((SingleProduct) product.getType()).getAmount())) {
                throw new InvalidAmountException(product.getName());
            }
        }
        products.add(product);
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
}
