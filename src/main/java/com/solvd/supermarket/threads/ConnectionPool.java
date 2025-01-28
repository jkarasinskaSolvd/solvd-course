package com.solvd.supermarket.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    Integer capacity;
    BlockingQueue<MockedConnection> blockingQueue;

    private static volatile ConnectionPool instance = null;

    private ConnectionPool(Integer capacity) {
        this.capacity = capacity;
        this.blockingQueue = new LinkedBlockingDeque<>(capacity);
        for (int i = 0; i < capacity; i++) {
            blockingQueue.offer(new MockedConnection(i, "host"+i));
        }
    }

    public static ConnectionPool getInstance(Integer capacity) {
        ConnectionPool result = instance;
        if (result == null) {
            synchronized (ConnectionPool.class) {
                result = instance;
                if (result == null)
                    instance = result = new ConnectionPool(capacity);
            }
        }
        return result;
    }

    public MockedConnection getMockedConnection() throws InterruptedException {
        return blockingQueue.take();
    }

    public void releaseMockedConnection(MockedConnection mockedConnection) {
        blockingQueue.offer(mockedConnection);
    }
}
