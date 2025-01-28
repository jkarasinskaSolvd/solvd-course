package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.FlightDAO;
import com.solvd.airport.model.Flight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl implements FlightDAO {
    private ConnectionPool pool;

    public FlightDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Flight findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Flights where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Flight flight = new Flight();
                Long idParam = rs.getLong("id");
                LocalDateTime startTime = rs.getTimestamp("start_time").toLocalDateTime();
                LocalDateTime landingTime = rs.getTimestamp("landing_time").toLocalDateTime();
                Long planeId = rs.getLong("plane_id");
                Long startAirportId = rs.getLong("start_airport_id");
                Long destinationAirportId = rs.getLong("destination_airport_id");
                Long firstPilotId = rs.getLong("first_pilot_id");
                Long secondPilotId = rs.getLong("second_pilot_id");


                flight.setId(idParam);
                flight.setStartTime(startTime);
                flight.setLandingTime(landingTime);
                flight.setPlaneId(planeId);
                flight.setStartAirportId(startAirportId);
                flight.setDestinationAirportId(destinationAirportId);
                flight.setFirstPilotId(firstPilotId);
                flight.setSecondPilotId(secondPilotId);

                pool.releaseConnection(connection);
                return flight;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(Flight entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query =
                    "insert into Flights(id, start_time, landing_time, plane_id, start_airport_id, " +
                            "destination_airport_id,first_pilot_id, second_pilot_id) " +
                            "values (" + entity.getId() +", \"" + entity.getStartTime()
                            + "\", \"" + entity.getLandingTime() + "\", " + entity.getPlaneId()
                            + ", " + entity.getStartAirportId() + ", " + entity.getDestinationAirportId()
                            + ", " + entity.getFirstPilotId() + ", " + entity.getSecondPilotId() + ")";

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Flight flight) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Flights set start_time = \"" + flight.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                    "\", landing_time = \"" + flight.getLandingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                    "\", plane_id = " + flight.getPlaneId() +
                    ", start_airport_id = " + flight.getStartAirportId() +
                    ", destination_airport_id = " + flight.getDestinationAirportId() +
                    ", first_pilot_id = " + flight.getFirstPilotId() +
                    ", second_pilot_id = " + flight.getSecondPilotId() +
                    " where id = " + flight.getId();
            System.out.println(query);
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
            String query = "delete from Flights where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<Flight>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Flights";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Flight flight = new Flight();
                Long idParam = rs.getLong("id");
                LocalDateTime startTime = rs.getTimestamp("start_time").toLocalDateTime();
                LocalDateTime landingTime = rs.getTimestamp("landing_time").toLocalDateTime();
                Long planeId = rs.getLong("plane_id");
                Long startAirportId = rs.getLong("start_airport_id");
                Long destinationAirportId = rs.getLong("destination_airport_id");
                Long firstPilotId = rs.getLong("first_pilot_id");
                Long secondPilotId = rs.getLong("second_pilot_id");


                flight.setId(idParam);
                flight.setStartTime(startTime);
                flight.setLandingTime(landingTime);
                flight.setPlaneId(planeId);
                flight.setStartAirportId(startAirportId);
                flight.setDestinationAirportId(destinationAirportId);
                flight.setFirstPilotId(firstPilotId);
                flight.setSecondPilotId(secondPilotId);

                flights.add(flight);
            }
                pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return flights;
    }
}
