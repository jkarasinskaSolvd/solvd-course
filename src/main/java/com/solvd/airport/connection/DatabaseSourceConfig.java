package com.solvd.airport.connection;

public class DatabaseSourceConfig {
    private String url;
    private String user;
    private String password;

    public DatabaseSourceConfig() {
    }

    public DatabaseSourceConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
