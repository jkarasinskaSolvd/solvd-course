package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.IPlaneDAO;
import com.solvd.airport.model.Plane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAO implements IPlaneDAO {
    private final ConnectionPool pool;

    public PlaneDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Plane findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Planes where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                Plane plane = new Plane();
                plane.setId(rs.getLong("id"));
                plane.setRegistration(rs.getString("registration"));
                plane.setNumberOfSeats(rs.getInt("number_of_seats"));
                plane.setProductionYear(rs.getInt("production_year"));
                plane.setPlaneTypeId(rs.getLong("plane_type_id"));
                plane.setHangarId(rs.getLong("hangar_id"));
                plane.setAirlineId(rs.getLong("airlane_id"));

                return plane;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean save(Plane entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement(
                    "insert into Planes(id, registration, number_of_seats, production_year, plane_type_id" +
                            ", hangar_id, airlane_id) values (?,?,?,?,?,?,?)");
            prstmt.setLong(1, entity.getId());
            prstmt.setString(2, entity.getRegistration());
            prstmt.setInt(3, entity.getNumberOfSeats());
            prstmt.setInt(4, entity.getProductionYear());
            prstmt.setLong(5, entity.getPlaneTypeId());
            prstmt.setLong(6, entity.getHangarId());
            prstmt.setLong(7, entity.getAirlineId());
            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(Plane entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement(
                    "update Planes set registration = ?, number_of_seats = ?, production_year = ?, " +
                            "plane_type_id =?, hangar_id = ?, airlane_id = ? where id = ?");
            prstmt.setString(1, entity.getRegistration());
            prstmt.setInt(2, entity.getNumberOfSeats());
            prstmt.setInt(3, entity.getProductionYear());
            prstmt.setLong(4, entity.getPlaneTypeId());
            prstmt.setLong(5, entity.getHangarId());
            prstmt.setLong(6, entity.getAirlineId());
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Planes where id = ?");
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
    public List<Plane> findAll() {
        List<Plane> planes = new ArrayList<Plane>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Planes");
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                Plane plane = new Plane();
                plane.setId(rs.getLong("id"));
                plane.setRegistration(rs.getString("registration"));
                plane.setNumberOfSeats(rs.getInt("number_of_seats"));
                plane.setProductionYear(rs.getInt("production_year"));
                plane.setPlaneTypeId(rs.getLong("plane_type_id"));
                plane.setHangarId(rs.getLong("hangar_id"));
                plane.setAirlineId(rs.getLong("airlane_id"));
                planes.add(plane);
            }

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return planes;
    }
}
