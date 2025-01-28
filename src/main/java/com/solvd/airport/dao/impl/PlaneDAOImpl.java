package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.PlaneDAO;
import com.solvd.airport.model.Plane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAOImpl implements PlaneDAO {
    private ConnectionPool pool;

    public PlaneDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Plane findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Planes where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Plane plane = new Plane();
                Long idParam = rs.getLong("id");
                String registration = rs.getString("registration");
                Integer numberOfSeats = rs.getInt("number_of_seats");
                Integer productionYear = rs.getInt("production_year");
                Long planeTypeId = rs.getLong("plane_type_id");
                Long hangarId = rs.getLong("hangar_id");
                Long airlaneId = rs.getLong("airlane_id");

                plane.setId(idParam);
                plane.setRegistration(registration);
                plane.setNumberOfSeats(numberOfSeats);
                plane.setProductionYear(productionYear);
                plane.setPlaneTypeId(planeTypeId);
                plane.setHangarId(hangarId);
                plane.setAirlaneId(airlaneId);

                pool.releaseConnection(connection);
                return plane;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(Plane entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query =
                    "insert into Planes(id, registration, number_of_seats, production_year, plane_type_id, hangar_id, " +
                            "airlane_id) " +
                            "values (" + entity.getId() + ", '" + entity.getRegistration() + "', "
                            + entity.getNumberOfSeats() + ", " + entity.getProductionYear() + ", " + entity.getPlaneTypeId() + ", " +
                            entity.getHangarId() + ", " + entity.getAirlaneId() + ")";



            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Plane entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Planes set registration = \"" + entity.getRegistration()
                    + "\", number_of_seats = " + entity.getNumberOfSeats() + ", " +
                    "production_year = " + entity.getProductionYear() + ", " +
                    "plane_type_id = " + entity.getPlaneTypeId() + ", " +
                    "hangar_id = " + entity.getHangarId() + ", " +
                    "airlane_id = " + entity.getAirlaneId() + " where id = " + entity.getId();

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
            String query = "delete from Planes where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Plane> findAll() {
        List<Plane> planes = new ArrayList<Plane>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Planes";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Plane plane = new Plane();
                Long idParam = rs.getLong("id");
                String registration = rs.getString("registration");
                Integer numberOfSeats = rs.getInt("number_of_seats");
                Integer productionYear = rs.getInt("production_year");
                Long planeTypeId = rs.getLong("plane_type_id");
                Long hangarId = rs.getLong("hangar_id");
                Long airlaneId = rs.getLong("airlane_id");

                plane.setId(idParam);
                plane.setRegistration(registration);
                plane.setNumberOfSeats(numberOfSeats);
                plane.setProductionYear(productionYear);
                plane.setPlaneTypeId(planeTypeId);
                plane.setHangarId(hangarId);
                plane.setAirlaneId(airlaneId);

                planes.add(plane);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return planes;
    }
}
