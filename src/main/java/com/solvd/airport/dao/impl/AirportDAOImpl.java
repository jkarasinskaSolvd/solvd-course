package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.AirportDAO;
import com.solvd.airport.model.Airport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirportDAOImpl implements AirportDAO {
    private ConnectionPool pool;

    public AirportDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Airport findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Airports where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Airport airport = new Airport();
                Long idParam = rs.getLong("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                Long addressId = rs.getLong("address_id");
                airport.setId(idParam);
                airport.setName(name);
                airport.setCode(code);
                airport.setAddressId(addressId);
                pool.releaseConnection(connection);
                return airport;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(Airport entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into Airports(id, name, code, address_id) values (" + entity.getId() +", \"" + entity.getName()
                    + "\", \"" + entity.getCode() + "\","+entity.getAddressId()+")";

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Airport entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Airports set name = \"" + entity.getName() + "\", code = \"" + entity.getCode()
                    + "\", address_id = " + entity.getAddressId() + " where id = " + entity.getId();

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
            String query = "delete from Airports where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Airport> findAll() {
        List<Airport> airports = new ArrayList<Airport>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Airports";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Airport airport = new Airport();
                Long idParam = rs.getLong("id");
                String name = rs.getString("name");
                String code = rs.getString("code");
                Long addressId = rs.getLong("address_id");
                airport.setId(idParam);
                airport.setName(name);
                airport.setCode(code);
                airport.setAddressId(addressId);
                pool.releaseConnection(connection);
                airports.add(airport);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return airports;
    }
}
