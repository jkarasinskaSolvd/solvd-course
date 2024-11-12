package org.solvd.transaction;

import org.solvd.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<Product>(); //ArrayList is implementation of List interface
    }

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Double totalPrice() {
        Double total = 0.0;
        for (Product product : products) {
            total += product.getType().calculatePrice();
        }
        return total;
    }

    public void printProducts() {
        for (Product product : products) {
            System.out.println(product.toString() + "\n");
        }
    }
}
