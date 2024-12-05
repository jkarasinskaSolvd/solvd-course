package com.solvd.transaction;

import com.solvd.exception.FileSaveFailureException;
import com.solvd.exception.InvalidPaymentMethodException;
import com.solvd.exception.ObjectCreationFailureException;
import com.solvd.storage.StoragePlace;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class Transaction {
    private Register register;
    private ShoppingCart cart;
    private PaymentMethod paymentMethod;
    private Double value;
    private LocalDateTime date;
    private Client client;

    public Transaction() {
    }

    public Transaction(Register register, ShoppingCart cart, PaymentMethod paymentMethod, LocalDateTime date)
            throws ObjectCreationFailureException {
        this.register = register;
        this.cart = cart;
        try{
            if(this.register instanceof ContactlessRegister && paymentMethod == PaymentMethod.CASH){
                throw new InvalidPaymentMethodException();
            }
        }catch(InvalidPaymentMethodException e){
            throw new ObjectCreationFailureException(e.getMessage());
        }
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private void printReceipt(){
        System.out.print("\n\nReceipt: \n\n");
        cart.printProducts();
        System.out.printf("Total price: " + cart.totalPrice());
        if(client!=null){
            System.out.println("Client loyalty card number: " + client.getLoyaltyCardNumber());
        }
    }

    private void removeFromStock(List<StoragePlace> storagePlaceList){
        for(int i = 0; i<cart.getProducts().size(); i++){
            for(StoragePlace storagePlace : storagePlaceList){
                storagePlace.removeProduct(cart.getProducts().get(i));
            }
        }
    }

    public void finishTransaction(List<StoragePlace> storagePlaceList) throws FileSaveFailureException{
        value = cart.totalPrice();
        printReceipt();
        System.out.printf("\nTransaction finished. Total price: $%.2f\n", value);
        removeFromStock(storagePlaceList);
        saveReceiptToFile();
    }

    public void saveReceiptToFile() throws FileSaveFailureException {
        try(BufferedWriter myWriter = new BufferedWriter(new FileWriter
                ("receipt"+date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"))+".txt"))){
            myWriter.write("Receipt: \n\n");
            for (int i = 0; i<cart.getProducts().size(); i++) {
                myWriter.write(cart.getProducts().get(i).toString() + "\n");
            }
            myWriter.write("\nTotal price: " + cart.totalPrice());
            myWriter.flush();
        } catch (IOException e) {
            throw new FileSaveFailureException();
        }
    }

}
