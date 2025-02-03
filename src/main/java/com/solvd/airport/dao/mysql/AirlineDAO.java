package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.IAirlineDAO;
import com.solvd.airport.model.Airline;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAO implements IAirlineDAO {
    private ConnectionPool pool;

    public AirlineDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Airline findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Airlines where id = ?");
            prstmt.setLong(1, id);

            ResultSet rs = prstmt.executeQuery();
            if (rs.next()) {
                Airline airline = new Airline();
                airline.setId(rs.getLong("id"));
                airline.setName(rs.getString("name"));
                airline.setRegistrationCountryId(rs.getLong("registrationCountryId"));
                return airline;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean save(Airline entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("insert into " +
                    "Airlines(id, name, registration_country_id) values (?,?,?)");
            prstmt.setLong(1, entity.getId());
            prstmt.setString(2, entity.getName());
            prstmt.setLong(3, entity.getRegistrationCountryId());
            prstmt.executeUpdate();

            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(Airline entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("update Airlines set name = ?, " +
                    "registration_country_id = ? where id = ?");
            prstmt.setString(1, entity.getName());
            prstmt.setLong(2, entity.getRegistrationCountryId());
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Airlines where id = ?");
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
    public List<Airline> findAll() {
        List<Airline> airlines = new ArrayList<Airline>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Airlines");

            ResultSet rs = prstmt.executeQuery();
            while (rs.next()) {
                Airline airline = new Airline();
                airline.setId(rs.getLong("id"));
                airline.setName(rs.getString("name"));
                airline.setRegistrationCountryId(rs.getLong("registrationCountryId"));

                airlines.add(airline);
            }


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return airlines;
    }
}
