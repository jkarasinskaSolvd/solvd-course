package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.HangarDAO;
import com.solvd.airport.model.Hangar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HangarDAOImpl implements HangarDAO {
    private ConnectionPool pool;

    public HangarDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Hangar findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Hangars where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Hangar hangar = new Hangar();
                Long idParam = rs.getLong("id");
                String name = rs.getString("name");
                Integer capacity = rs.getInt("capacity");
                Long airportId = rs.getLong("airport_id");

                hangar.setId(idParam);
                hangar.setName(name);
                hangar.setCapacity(capacity);
                hangar.setAirportId(airportId);
                pool.releaseConnection(connection);
                return hangar;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(Hangar entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into Hangars(id, name, capacity, airport_id) values ( " + entity.getId() + ", \""
                    + entity.getName() + "\",  " + entity.getCapacity() + ", " + entity.getAirportId() + ")";

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Hangar entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Hangars set name = \"" + entity.getName() + "\", capacity = " + entity.getCapacity()
                    + ", airport_id = " + entity.getAirportId() + " where id = " + entity.getId();

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
            String query = "delete from Hangars where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Hangar> findAll() {
        List<Hangar> hangars = new ArrayList<Hangar>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Hangars";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Hangar hangar = new Hangar();
                Long idParam = rs.getLong("id");
                String name = rs.getString("name");
                Integer capacity = rs.getInt("capacity");
                Long airportId = rs.getLong("airport_id");

                hangar.setId(idParam);
                hangar.setName(name);
                hangar.setCapacity(capacity);
                hangar.setAirportId(airportId);

                hangars.add(hangar);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return hangars;
    }
}
