package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.IAddressDAO;
import com.solvd.airport.model.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements IAddressDAO {
    private ConnectionPool pool;

    public AddressDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Address findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Addresses where id = ?");
            prstmt.setLong(1, id);

            ResultSet rs = prstmt.executeQuery();
            if (rs.next()) {
                Address address = new Address();

                address.setId(rs.getLong("id"));
                address.setCity(rs.getString("city"));
                address.setPostCode(rs.getString("post_code"));
                address.setStreet(rs.getString("street"));
                address.setStreetNumber(rs.getString("street_number"));
                address.setApartmentNumber(rs.getString("apartment_number"));
                address.setCountryId(rs.getLong("contry_id"));

                return address;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }finally{
            pool.releaseConnection(connection);

        }
    }

    @Override
    public Boolean save(Address entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("insert into " +
                            "Addresses(id, city, post_code, street, street_number, apartment_number, country_id) " +
                            "values (?,?,?,?,?,?,?)");

            prstmt.setLong(1, entity.getId());
            prstmt.setString(2, entity.getCity());
            prstmt.setString(3, entity.getPostCode());
            prstmt.setString(4, entity.getStreet());
            prstmt.setString(5, entity.getStreetNumber());
            prstmt.setString(6, entity.getApartmentNumber());
            prstmt.setLong(7, entity.getCountryId());
            prstmt.executeUpdate();

            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(Address entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("update Addresses set city = ?, post_code = ?," +
                    "street = ?, street_number = ?, apartment_number = ?, country_id = ? where id = ?");
            prstmt.setString(1,entity.getCity());
            prstmt.setString(2,entity.getPostCode());
            prstmt.setString(3,entity.getStreet());
            prstmt.setString(4,entity.getStreetNumber());
            prstmt.setString(5,entity.getApartmentNumber());
            prstmt.setLong(6,entity.getCountryId());
            prstmt.setLong(7, entity.getId());

            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean delete(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("delete from Addresses where id = ?");
            prstmt.setLong(1,id);
            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<Address>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Addresses");
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getLong("id"));
                address.setCity(rs.getString("city"));
                address.setPostCode(rs.getString("post_code"));
                address.setStreet(rs.getString("street"));
                address.setStreetNumber(rs.getString("street_number"));
                address.setApartmentNumber(rs.getString("apartment_number"));
                address.setCountryId(rs.getLong("contry_id"));

                addresses.add(address);
            }


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return addresses;
    }
}