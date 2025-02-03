package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.IFlightDAO;
import com.solvd.airport.model.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO implements IFlightDAO {
    private final ConnectionPool pool;

    public FlightDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Flight findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Flights where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getLong("id"));
                flight.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                flight.setLandingTime(rs.getTimestamp("landing_time").toLocalDateTime());
                flight.setPlaneId(rs.getLong("plane_id"));
                flight.setStartAirportId(rs.getLong("start_airport_id"));
                flight.setDestinationAirportId(rs.getLong("destination_airport_id"));
                flight.setFirstPilotId(rs.getLong("first_pilot_id"));
                flight.setSecondPilotId(rs.getLong("second_pilot_id"));

                return flight;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean save(Flight entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("insert into Flights(id, start_time, " +
                    "landing_time, plane_id, start_airport_id, destination_airport_id,first_pilot_id, second_pilot_id )" +
                    "values (?,?,?,?,?,?,?,?) " );
            prstmt.setLong(1, entity.getId());
            prstmt.setTimestamp(2,Timestamp.valueOf(entity.getStartTime()));
            prstmt.setTimestamp(3,Timestamp.valueOf(entity.getLandingTime()));
            prstmt.setLong(4,entity.getPlaneId());
            prstmt.setLong(5,entity.getStartAirportId());
            prstmt.setLong(6,entity.getDestinationAirportId());
            prstmt.setLong(7,entity.getFirstPilotId());
            prstmt.setLong(8,entity.getSecondPilotId());

            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(Flight flight) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("update Flights set start_time = ?" +
                    ", landing_time = ?,  plane_id = ?, start_airport_id = ?, destination_airport_id = ?" +
                    ", first_pilot_id = ?, second_pilot_id = ? where id = ?" );
            prstmt.setTimestamp(1, Timestamp.valueOf(flight.getStartTime()));
            prstmt.setTimestamp(2, Timestamp.valueOf(flight.getLandingTime()));
            prstmt.setLong(3, flight.getPlaneId());
            prstmt.setLong(4, flight.getStartAirportId());
            prstmt.setLong(5, flight.getDestinationAirportId());
            prstmt.setLong(6, flight.getFirstPilotId());
            prstmt.setLong(7, flight.getSecondPilotId());
            prstmt.setLong(8, flight.getId());
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Flights where id = ?");
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
    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<Flight>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Flights");
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getLong("id"));
                flight.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                flight.setLandingTime(rs.getTimestamp("landing_time").toLocalDateTime());
                flight.setPlaneId(rs.getLong("plane_id"));
                flight.setStartAirportId(rs.getLong("start_airport_id"));
                flight.setDestinationAirportId(rs.getLong("destination_airport_id"));
                flight.setFirstPilotId(rs.getLong("first_pilot_id"));
                flight.setSecondPilotId(rs.getLong("second_pilot_id"));
                flights.add(flight);
            }


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return flights;
    }
}
