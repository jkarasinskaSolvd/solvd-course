package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.ICountryDAO;
import com.solvd.airport.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements ICountryDAO {

    private ConnectionPool pool;

    public CountryDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Country findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Countries where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                Country country = new Country();
                country.setId(rs.getLong("id"));
                country.setName(rs.getString("name"));
                country.setCode(rs.getString("code"));
                return country;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean save(Country entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("insert into Countries (id,name, code) " +
                                                                            "values (?, ?, ?)");
            prstmt.setString(1, entity.getName());
            prstmt.setString(2, entity.getCode());
            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(Country entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("update Countries set name = ?, code = ? " +
                                                                            "where id = ?");
            prstmt.setString(1, entity.getName());
            prstmt.setString(2, entity.getCode());
            prstmt.setLong(3, entity.getId());
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Countries where id = ?");
            prstmt.setLong(1, id);
            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<Country>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Countries");
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getLong("id"));
                country.setName(rs.getString("name"));
                country.setCode(rs.getString("code"));
            }

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return countries;
    }
}
