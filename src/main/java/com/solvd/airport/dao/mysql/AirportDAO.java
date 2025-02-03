package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.IAirportDAO;
import com.solvd.airport.model.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportDAO implements IAirportDAO {
    private ConnectionPool pool;

    public AirportDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Airport findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Airports where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                Airport airport = new Airport();
                airport.setId(rs.getLong("id"));
                airport.setName(rs.getString("name"));
                airport.setCode(rs.getString("code"));
                airport.setAddressId(rs.getLong("address_id"));
                return airport;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);

        }
    }

    @Override
    public Boolean save(Airport entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("insert into " +
                    "Airports(id, name, code, address_id) values (?,?,?,?)");
            prstmt.setLong(1, entity.getId());
            prstmt.setString(2, entity.getName());
            prstmt.setString(3, entity.getCode());
            prstmt.setLong(4, entity.getAddressId());
            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(Airport entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement prstmt = connection.prepareStatement("update Airports set name = ?, code = ? " +
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Airports where id = ?");
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
    public List<Airport> findAll() {
        List<Airport> airports = new ArrayList<Airport>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Airports");
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                Airport airport = new Airport();
                airport.setId(rs.getLong("id"));
                airport.setName(rs.getString("name"));
                airport.setCode(rs.getString("code"));
                airport.setAddressId(rs.getLong("address_id"));
                pool.releaseConnection(connection);
                airports.add(airport);
            }

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return airports;
    }
}
