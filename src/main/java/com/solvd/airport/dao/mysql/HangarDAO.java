package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.IHangarDAO;
import com.solvd.airport.model.Hangar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HangarDAO implements IHangarDAO {
    private final ConnectionPool pool;

    public HangarDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Hangar findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Hangars where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                Hangar hangar = new Hangar();
                hangar.setId(rs.getLong("id"));
                hangar.setName(rs.getString("name"));
                hangar.setCapacity(rs.getInt("capacity"));
                hangar.setAirportId(rs.getLong("airport_id"));
                return hangar;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean save(Hangar entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement(
                    "insert into Hangars(id, name, capacity, airport_id) values (?,?,?,?)");
            prstmt.setLong(1, entity.getId());
            prstmt.setString(2, entity.getName());
            prstmt.setInt(3, entity.getCapacity());
            prstmt.setLong(4, entity.getAirportId());
            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(Hangar entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("update Hangars set name = ?, capacity = ?" +
                    ", airport_id = ? where id = ?");
            prstmt.setString(1, entity.getName());
            prstmt.setInt(2, entity.getCapacity());
            prstmt.setLong(3, entity.getAirportId());
            prstmt.setLong(4, entity.getId());
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Hangars where id = ?");
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
    public List<Hangar> findAll() {
        List<Hangar> hangars = new ArrayList<Hangar>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Hangars");
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                Hangar hangar = new Hangar();
                hangar.setId(rs.getLong("id"));
                hangar.setName(rs.getString("name"));
                hangar.setCapacity(rs.getInt("capacity"));
                hangar.setAirportId(rs.getLong("airport_id"));
                hangars.add(hangar);
            }

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return hangars;
    }
}
