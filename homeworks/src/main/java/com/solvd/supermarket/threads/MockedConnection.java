package com.solvd.supermarket.threads;

public class MockedConnection {
    private Integer id;
    private String host;

    public MockedConnection() {
    }

    public MockedConnection(Integer id, String host) {
        this.id = id;
        this.host = host;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
