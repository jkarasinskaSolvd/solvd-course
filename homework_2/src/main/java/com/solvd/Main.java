package com.solvd;

import com.solvd.exception.FileSaveFailureException;
import com.solvd.exception.ObjectCreationFailureException;
import com.solvd.product.*;
import com.solvd.storage.*;
import com.solvd.transaction.*;


import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket("Polmart","Warsaw","Zlota","13",new Warehouse());

        System.out.printf("Supermarket id: "+ supermarket.getSupermarketId()+ "\n");

        //creating storage places in supermarket
        Fridge fridge1;

        try {
            fridge1 = new Fridge("Fridge1", Category.DIARY,5.0);
            fridge1.setLocation("1. row, 1. on the left side.");
            supermarket.getStoragePlaceList().add(fridge1);
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        Refrigerator refrigerator1;

        try {
            refrigerator1 = new Refrigerator("Refrigerator1", Category.MEAT,-20.0);
            refrigerator1.setLocation("1. row, 2. on the left side ");
            supermarket.getStoragePlaceList().add(refrigerator1);
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        Shelf shelf1 = new Shelf("Shelf1",Category.BOOKS);
        shelf1.setLocation("1. row, 1. on the right side ");
        supermarket.getStoragePlaceList().add(shelf1);

        supermarket.summarize();

        //add cashier to supermarket
        Cashier cashier = new Cashier("Anna",1,"Warsaw","Miejska","7",
                1200.0,"ISBN123456789");
        supermarket.getCashierList().add(cashier);

        //creating registers in supermarket
        Register register1 = new ContactlessRegister(1);
        register1.setLocation("1. register on the left side ");
        supermarket.getRegisterList().add(register1);
        Register register2 = new PhysicalRegister(2,cashier);
        register2.setLocation("1. register on the right side ");
        supermarket.getRegisterList().add(register2);

        //creating storage places in supermarket's warehouse
        Fridge fridgeWarehouse;

        try {
            fridgeWarehouse = new Fridge("FridgeWarehouse", Category.DIARY,5.0);
            supermarket.getWarehouse().getPlaces().add(fridgeWarehouse);
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        Refrigerator refrigeratorWarehouse;

        try {
            refrigeratorWarehouse = new Refrigerator("RefrigeratorWarehouse", Category.MEAT,
                    -20.0);
            supermarket.getWarehouse().getPlaces().add(refrigeratorWarehouse);
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        Shelf shelfWarehouse = new Shelf("ShelfWarehouse",Category.BOOKS);
        supermarket.getWarehouse().getPlaces().add(shelfWarehouse);

        supermarket.getWarehouse().summarize();

        //creating products for storage
        Product milk = new Product("milk", 1, "bottle of milk",
                new SingleProduct(3.0,4), StorageMethod.FRIDGE, Category.DIARY);
        Product cheese = new Product("cheese", 2, "block of cheese",
                new WeightedProduct(5.0,2.0), StorageMethod.FRIDGE, Category.DIARY);
        Product yoghurt = new Product("yoghurt", 3, "cup of yoghurt 250g",
                new SingleProduct(0.50,7), StorageMethod.FRIDGE, Category.DIARY);

        //creating products for shopping cart
        Product milk2 = new Product("milk", 1, "bottle of milk",
                new SingleProduct(3.0,2), StorageMethod.FRIDGE, Category.DIARY);
        Product cheese2 = new Product("cheese", 2, "block of cheese",
                new WeightedProduct(5.0,1.0), StorageMethod.FRIDGE, Category.DIARY);
        Product yoghurt2 = new Product("yoghurt", 3, "cup of yoghurt 250g",
                new SingleProduct(0.50,7), StorageMethod.FRIDGE, Category.DIARY);

        //adding products to warehouse
        supermarket.getWarehouse().getPlaces().getFirst().addProduct(milk);
        supermarket.getWarehouse().getPlaces().getFirst().addProduct(cheese);
        supermarket.getWarehouse().getPlaces().getFirst().addProduct(yoghurt);

        supermarket.getWarehouse().summarize();

        //moving products to supermarket fridge
        supermarket.getWarehouse().unpackStoragePlace(supermarket.getWarehouse().getPlaces().getFirst(),
                supermarket.getStoragePlaceList().getFirst());

        System.out.print("\nAfter moving products from warehouse: \n\n" );
        supermarket.summarize();

        //creating shopping cart and adding products
        ShoppingCart cart= new ShoppingCart();
        cart.addProduct(milk2);
        cart.addProduct(cheese2);
        cart.addProduct(yoghurt2);

        //transaction
        Transaction transaction = null;
        try {
            transaction = new Transaction(register1,cart, PaymentMethod.CARD,
                    LocalDateTime.now());
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        try{
            assert transaction != null;
            transaction.finishTransaction(supermarket.getStoragePlaceList());

        }catch (FileSaveFailureException e){
            System.out.println(e.getMessage());
        }

        System.out.print("\nAfter finishing transaction: \n\n" );
        supermarket.summarize();

    }
}