package org.solvd.transaction;

import org.solvd.product.Product;
import org.solvd.storage.StoragePlace;

import java.time.LocalDateTime;
import java.util.List;

public class Transaction {
    private Register register;
    private ShoppingCart cart;
    private PaymentMethod paymentMethod;
    private Double value;
    private LocalDateTime date;

    public Transaction() {
    }

    public Transaction(Register register, ShoppingCart cart, PaymentMethod paymentMethod, LocalDateTime date) {
        this.register = register;
        this.cart = cart;
        this.paymentMethod = paymentMethod;
        value = 0.0;
        this.date = date;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private void printReceipt(){
        System.out.printf("\n\nReceipt: \n\n");
        cart.printProducts();
        System.out.printf("Total price: " + cart.totalPrice());
    }

    private void removeFromStock(List<StoragePlace> storagePlaceList){
        for(Product product : cart.getProducts()){
            for(StoragePlace storagePlace : storagePlaceList){
                storagePlace.removeProduct(product);
            }
        }
    }

    public void finishTransaction(List<StoragePlace> storagePlaceList){
        value = cart.totalPrice();
        printReceipt();
        System.out.printf("\nTransaction finished. Total price: $%.2f\n", value);
        removeFromStock(storagePlaceList);
    }


}
