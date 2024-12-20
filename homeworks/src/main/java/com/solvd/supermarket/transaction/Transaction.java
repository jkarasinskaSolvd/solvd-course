package com.solvd.supermarket.transaction;

import com.solvd.supermarket.exception.FileSaveFailureException;
import com.solvd.supermarket.exception.InvalidPaymentMethodException;
import com.solvd.supermarket.exception.ObjectCreationFailureException;
import com.solvd.supermarket.storage.StoragePlace;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

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
            BiPredicate<Register, PaymentMethod> correctTransaction =
                    (registerParam, paymentMethodParam) -> registerParam instanceof ContactlessRegister
                            && paymentMethodParam.equals(PaymentMethod.CASH);
            if(correctTransaction.test(register,paymentMethod)){
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

    private String printReceipt(){
        StringBuilder builder = new StringBuilder();
        builder.append("Receipt: \n\n");
        cart.getProducts().stream().forEach(product -> builder.append(product).append("\n"));
        builder.append("\nTotal price: ").append(value).append("\n\n");
        if(client!=null){
            builder.append("Client loyalty card number: ").append(client.getLoyaltyCard().getLoyaltyCardId());
            if(paymentMethod.getContactless()){
                builder.append("Contactless transaction - well done!");
            }
        }
        if(paymentMethod.getTraceable()){
            System.out.println("This transaction will be traceable");
        }

        return builder.toString();
    }

    private void removeFromStock(List<StoragePlace> storagePlaceList){
        for(int i = 0; i<cart.getProducts().size(); i++){
            for(StoragePlace storagePlace : storagePlaceList){
                storagePlace.removeProduct(cart.getProducts().get(i));
            }
        }
    }

    private Double calculateTransactionValue(){
        double currentValue =  cart.totalPrice() * paymentMethod.calculateTaxModifier();
        if(Objects.nonNull(client)){
            currentValue *= client.getLoyaltyCard().getDiscountType().calculateDiscountModifier();
        }
        return currentValue;
    }

    public void finishTransaction(List<StoragePlace> storagePlaceList) throws FileSaveFailureException{
        value = calculateTransactionValue();
        System.out.println(printReceipt());
        System.out.printf("\nTransaction finished. Total price: $%.2f\n", value);
        removeFromStock(storagePlaceList);
        saveReceiptToFile();
    }

    public void saveReceiptToFile() throws FileSaveFailureException {

        try(BufferedWriter myWriter = new BufferedWriter(new FileWriter
                ("receipt"+date.format(DateTimeFormatter.ofPattern("yyyy_MM_dd"))+".txt"))){

            myWriter.write(printReceipt());
            myWriter.flush();
        } catch (IOException e) {
            throw new FileSaveFailureException();
        }
    }

}
