package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.AirlineDAO;
import com.solvd.airport.model.Airline;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAOImpl implements AirlineDAO {
    private ConnectionPool pool;

    public AirlineDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Airline findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Airlines where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Airline airline = new Airline();
                Long idParam = rs.getLong("id");
                String name = rs.getString("name");
                Long registrationCountryId = rs.getLong("registrationCountryId");
                airline.setId(idParam);
                airline.setName(name);
                airline.setRegistrationCountryId(registrationCountryId);
                pool.releaseConnection(connection);
                return airline;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(Airline entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into Airlines(id, name, registration_country_id) values (" + entity.getId() +", \""
                    + entity.getName() + "\", " + entity.getRegistrationCountryId() + ")";

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Airline entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Airline set name = \"" + entity.getName() + "\", registration_country_id = "
                    + entity.getRegistrationCountryId() + " where id = " + entity.getId();

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
            String query = "delete from Airlines where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Airline> findAll() {
        List<Airline> airlines = new ArrayList<Airline>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Airlines";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Airline airline = new Airline();
                Long idParam = rs.getLong("id");
                String name = rs.getString("name");
                Long registrationCountryId = rs.getLong("registrationCountryId");
                airline.setId(idParam);
                airline.setName(name);
                airline.setRegistrationCountryId(registrationCountryId);
                pool.releaseConnection(connection);
                airlines.add(airline);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return airlines;
    }
}
