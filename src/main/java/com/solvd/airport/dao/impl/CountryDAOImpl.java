package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.CountryDAO;
import com.solvd.airport.model.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {

    private ConnectionPool pool;

    public CountryDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Country findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Countries where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Country country = new Country();
                Long idParam = rs.getLong("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                country.setId(idParam);
                country.setName(name);
                country.setCode(code);
                pool.releaseConnection(connection);
                return country;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(Country entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into Countries(id, name, code) values (" + entity.getId() +", \"" + entity.getName()
                                                            + "\", \"" + entity.getCode() + "\")";

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Country entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Countries set name = \"" + entity.getName() + "\", code = \"" + entity.getCode()
                    + "\" where id = " + entity.getId();

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "delete from Countries where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<Country>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Countries";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Country country = new Country();
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                country.setId(id);
                country.setName(name);
                country.setCode(code);
                countries.add(country);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return countries;
    }
}
