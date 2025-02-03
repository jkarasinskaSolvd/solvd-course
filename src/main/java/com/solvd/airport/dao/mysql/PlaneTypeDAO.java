package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.IPlaneTypeDAO;
import com.solvd.airport.model.PlaneType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneTypeDAO implements IPlaneTypeDAO {
    private ConnectionPool pool;

    public PlaneTypeDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public PlaneType findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Plane_Types where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                PlaneType planeType = new PlaneType();
                planeType.setId(rs.getLong("id"));
                planeType.setName(rs.getString("name"));
                return planeType;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean save(PlaneType entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("insert into Plane_Types (id,name) " +
                    "values (?, ?)");
            prstmt.setLong(1, entity.getId());
            prstmt.setString(2, entity.getName());
            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(PlaneType entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("update Plane_Types set name = ? " +
                    "where id = ?");
            prstmt.setString(1, entity.getName());
            prstmt.setLong(2, entity.getId());
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Plane_Types where id = ?");
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
    public List<PlaneType> findAll() {
        List<PlaneType> planeTypes = new ArrayList<PlaneType>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Plane_Types");
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()) {
                PlaneType planeType = new PlaneType();
                planeType.setId(rs.getLong("id"));
                planeType.setName(rs.getString("name"));
            }

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return planeTypes;
    }
}
