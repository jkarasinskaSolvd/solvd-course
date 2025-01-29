package com.solvd.airport.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    Integer capacity;
    BlockingQueue<Connection> blockingQueue;

    private static volatile ConnectionPool instance = null;

    private ConnectionPool(Integer capacity, DatabaseSourceConfig databaseSourceConfig) throws SQLException, ClassNotFoundException {
        this.capacity = capacity;
        this.blockingQueue = new LinkedBlockingDeque<>(capacity);

        for (int i = 0; i < capacity; i++) {
             blockingQueue.offer(DriverManager.getConnection(databaseSourceConfig.getUrl(), databaseSourceConfig.getUser(), databaseSourceConfig.getPassword()));
        }
    }

    public static ConnectionPool getInstance(Integer capacity, DatabaseSourceConfig databaseSourceConfig) throws SQLException, ClassNotFoundException {
        ConnectionPool result = instance;
            if (result == null) {
                    synchronized (ConnectionPool.class) {
                    result = instance;
                    if (result == null)
                        instance = result = new ConnectionPool(capacity, databaseSourceConfig);
                }
            }
            return result;
    }

    public Connection getConnection() throws InterruptedException {
        return blockingQueue.take();
    }

    public void releaseConnection(Connection connection) {
        blockingQueue.offer(connection);
    }
}
