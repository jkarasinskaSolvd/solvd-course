package com.solvd.supermarket;

import com.solvd.supermarket.exception.FileSaveFailureException;
import com.solvd.supermarket.exception.InvalidAmountException;
import com.solvd.supermarket.exception.ObjectCreationFailureException;
import com.solvd.supermarket.product.*;
import com.solvd.supermarket.storage.*;
import com.solvd.supermarket.threads.ConnectionPool;
import com.solvd.supermarket.threads.SupermarketThread;
import com.solvd.supermarket.transaction.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket("Polmart", "Warsaw", "Zlota", "13", new Warehouse());

        System.out.printf("Supermarket id: " + supermarket.getSupermarketId() + "\n");

        //creating storage places in supermarket
        CoolingUnit fridge1;

        try {
            fridge1 = new CoolingUnit("Fridge1", Category.DIARY, StorageSize.MEDIUM, 5.0);
            fridge1.setLocation("1. row, 1. on the left side.");
            supermarket.getStoragePlaceList().add(fridge1);
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        CoolingUnit refrigerator1;

        try {
            refrigerator1 = new CoolingUnit("Refrigerator1", Category.MEAT, StorageSize.LARGE, -8.0);
            refrigerator1.setLocation("1. row, 2. on the left side ");
            supermarket.getStoragePlaceList().add(refrigerator1);
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        Shelf shelf1 = new Shelf("Shelf1", Category.BOOKS, StorageSize.SMALL);
        shelf1.setLocation("1. row, 1. on the right side ");
        supermarket.getStoragePlaceList().add(shelf1);

        supermarket.summarize();

        //add cashier to supermarket
        Cashier cashier = new Cashier("Anna", 1, "Warsaw", "Miejska", "7",
                1200.0, "ISBN123456789");
        supermarket.getCashierList().add(cashier);

        //creating registers in supermarket
        Register register1 = new ContactlessRegister(1);
        register1.setLocation("1. register on the left side ");
        supermarket.getRegisterList().add(register1);
        Register register2 = new PhysicalRegister(2, cashier);
        register2.setLocation("1. register on the right side ");
        supermarket.getRegisterList().add(register2);

        //creating storage places in supermarket's warehouse
        CoolingUnit fridgeWarehouse;

        try {
            fridgeWarehouse = new CoolingUnit("FridgeWarehouse", Category.DIARY, StorageSize.MEDIUM, 5.0);
            supermarket.getWarehouse().getPlaces().add(fridgeWarehouse);
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        CoolingUnit refrigeratorWarehouse;

        try {
            refrigeratorWarehouse = new CoolingUnit("RefrigeratorWarehouse", Category.MEAT, StorageSize.LARGE,
                    -5.0);
            supermarket.getWarehouse().getPlaces().add(refrigeratorWarehouse);
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        Shelf shelfWarehouse = new Shelf("ShelfWarehouse", Category.BOOKS, StorageSize.SMALL);
        supermarket.getWarehouse().getPlaces().add(shelfWarehouse);

        supermarket.getWarehouse().summarize();

        //creating products for storage

        //reflection

        Product banana = new Product();

        try {
            Class<?> productClass = Class.forName("com.solvd.supermarket.product.Product");
            Constructor<?>[]constructors = productClass.getConstructors();
            Constructor<?> noArgConstructor = Arrays
                    .stream(constructors)
                    .filter(constructor -> constructor.getParameterCount()==0)
                    .findFirst()
                    .get();
            banana = (Product)noArgConstructor.newInstance();

            Field[] fields = productClass.getDeclaredFields();
            Method[] methods = productClass.getDeclaredMethods();
//          Arrays.stream(fields).forEach(field -> System.out.println(field + "\n"));

            Optional<Method> setter = Arrays.stream(methods).filter(method->method.getName().contains("setCategory")).findFirst();
            if(setter.isPresent()){
                setter.get().setAccessible(true);
                setter.get().invoke(banana,Category.GREENGROCERIES);
            }

            System.out.println(banana.getName());

             setter = Arrays.stream(methods).filter(method->method.getName().contains("setName")).findFirst();
            if(setter.isPresent()){
                setter.get().setAccessible(true);
                setter.get().invoke(banana,"banana");
            }

            System.out.println(banana);

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Product milk = new Product("milk", 1, "bottle of milk",
                new SingleProduct(3.0, 4), StorageMethod.FRIDGE, Category.DIARY);
        Product cheese = new Product("cheese", 2, "block of cheese",
                new WeightedProduct(5.0, 2.0), StorageMethod.FRIDGE, Category.DIARY);
        Product yoghurt = new Product("yoghurt", 3, "cup of yoghurt 250g",
                new SingleProduct(0.50, 7), StorageMethod.FRIDGE, Category.DIARY);

        //creating products for shopping cart
        Product milk2 = new Product("milk", 1, "bottle of milk",
                new SingleProduct(3.0, 2), StorageMethod.FRIDGE, Category.DIARY);
        Product cheese2 = new Product("cheese", 2, "block of cheese",
                new WeightedProduct(5.0, 1.0), StorageMethod.FRIDGE, Category.DIARY);
        Product yoghurt2 = new Product("yoghurt", 3, "cup of yoghurt 250g",
                new SingleProduct(0.50, 7), StorageMethod.FRIDGE, Category.DIARY);

        //adding products to warehouse
        try {
            supermarket.getWarehouse().getPlaces().getFirst().addProduct(milk);
            supermarket.getWarehouse().getPlaces().getFirst().addProduct(cheese);
            supermarket.getWarehouse().getPlaces().getFirst().addProduct(yoghurt);
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }

        supermarket.getWarehouse().summarize();

        //moving products to supermarket fridge
        supermarket.getWarehouse().unpackStoragePlace(supermarket.getWarehouse().getPlaces().getFirst(),
                supermarket.getStoragePlaceList().getFirst());

        System.out.print("\nAfter moving products from warehouse: \n\n");
        supermarket.summarize();

        //creating shopping cart and adding products
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(milk2);
        cart.addProduct(cheese2);
        cart.addProduct(yoghurt2);
        cart.addProduct(banana);

        Optional<Product> mostValuableProduct = cart.getMostValuableProductByType(SingleProduct.class);
        if (mostValuableProduct.isPresent()) {
            System.out.println("Most valuable Single Product in the cart:" + mostValuableProduct.get() + "\n");
        } else {
            System.out.println("No Single Product found in the cart.\n");
        }

        mostValuableProduct = cart.getMostValuableProductByType(WeightedProduct.class);
        if (mostValuableProduct.isPresent()) {
            System.out.println("Most valuable Weighed Product in the cart:" + mostValuableProduct.get() + "\n");
        } else {
            System.out.println("No Weighed Product found in the cart.\n");
        }

        Stream.of(Category.values()).forEach(
                category -> System.out.println(
                        "Is product from "
                                + category.getName()
                                + " in the cart: "
                                + cart.checkIfCategoryInTheCart(category) + "\n")
        );

        System.out.println("Amount of products that are edible in the cart: "
                + cart.countProductsByCategoryParameter(product -> product.getCategory().getEdible()));

        System.out.println("Amount of products that are consumable in the cart: "
                + cart.countProductsByCategoryParameter(product -> product.getCategory().getConsumable()));

        System.out.println("Amount of products that need to be in the cold temperature in the cart: "
                + cart.countProductsByCategoryParameter(product -> product.getCategory().getNeedsColdTemperature()));
        //transaction
        Transaction transaction = null;
        try {
            transaction = new Transaction(register1, cart, PaymentMethod.CARD,
                    LocalDateTime.now());
        } catch (ObjectCreationFailureException e) {
            System.out.println(e.getMessage());
        }

        try {
            assert transaction != null;
            transaction.finishTransaction(supermarket.getStoragePlaceList());

        } catch (FileSaveFailureException e) {
            System.out.println(e.getMessage());
        }

        System.out.print("\nAfter finishing transaction: \n\n");
        supermarket.summarize();

        System.out.println(supermarket.displayPlacesToClean());


        ConnectionPool pool = ConnectionPool.getInstance(5);

        ExecutorService executor = Executors.newFixedThreadPool(7);

        executor.submit(new SupermarketThread(pool));
        executor.submit(new SupermarketThread(pool));
        executor.submit(new SupermarketThread(pool));
        executor.submit(new SupermarketThread(pool));
        executor.submit(new SupermarketThread(pool));
        executor.submit(new SupermarketThread(pool));
        executor.submit(new SupermarketThread(pool));

        executor.shutdown();


        for (int i = 0; i < 7; i++) {
            CompletableFuture.runAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                        Thread.currentThread().interrupt();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            };
        }

    }


