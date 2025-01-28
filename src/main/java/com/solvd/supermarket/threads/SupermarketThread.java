package com.solvd.supermarket.threads;

import com.solvd.supermarket.threads.ConnectionPool;

public class SupermarketThread extends Thread {
    private ConnectionPool pool;
    private static Integer counter = 0;
    private Integer counterValue;

    public SupermarketThread(ConnectionPool pool) {
        this.pool = pool;
        this.counterValue = counter;
        counter++;
    }

    @Override
    public void run() {
        try {
            MockedConnection connection = pool.getMockedConnection();
            System.out.println("Supermarket thread" + counterValue + " started");
            Thread.sleep(3000);
            System.out.println("Supermarket thread" + counterValue + " ended");
            pool.releaseMockedConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
