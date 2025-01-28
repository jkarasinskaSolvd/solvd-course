package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.AddressDAO;
import com.solvd.airport.model.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    private ConnectionPool pool;

    public AddressDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Address findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Addresses where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Address address = new Address();
                Long idParam = rs.getLong("id");
                String city = rs.getString("city");
                String postCode = rs.getString("post_code");
                String street = rs.getString("street");
                String streetNumber = rs.getString("street_number");
                String apartmentNumber = rs.getString("apartment_number");
                Long contryId = rs.getLong("contry_id");

                address.setId(idParam);

                address.setCity(city);
                address.setPostCode(postCode);
                address.setStreet(street);
                address.setStreetNumber(streetNumber);
                address.setApartmentNumber(apartmentNumber);
                address.setCountryId(contryId);

                pool.releaseConnection(connection);
                return address;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(Address entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query =
                    "insert into Addresses(id, city, post_code, street, street_number, apartment_number, country_id) " +
                            "values (" + entity.getId() +", \""+entity.getCity()+"\", \""+entity.getPostCode()+"\", \""
                            +entity.getStreet()+"\", \""+entity.getStreetNumber()+"\", \""+entity.getApartmentNumber()
                            +"\", "+entity.getCountryId()+")";


            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Address entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Addresses set city = \"" + entity.getCity() + "\", post_code = \""
                    + entity.getPostCode() + "\", street = \"" + entity.getStreet()
                    + "\", street_number = \"" + entity.getStreetNumber() + "\", apartment_number = \""
                    + entity.getApartmentNumber() + "\", country_id = " + entity.getCountryId()
                    + " where id = " + entity.getId();

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
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<Address>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Addresses";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Address address = new Address();
                Long idParam = rs.getLong("id");
                String city = rs.getString("city");
                String postCode = rs.getString("post_code");
                String street = rs.getString("street");
                String streetNumber = rs.getString("street_number");
                String apartmentNumber = rs.getString("apartment_number");
                Long contryId = rs.getLong("contry_id");

                address.setId(idParam);

                address.setCity(city);
                address.setPostCode(postCode);
                address.setStreet(street);
                address.setStreetNumber(streetNumber);
                address.setApartmentNumber(apartmentNumber);
                address.setCountryId(contryId);

                addresses.add(address);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return addresses;
    }
}