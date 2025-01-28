package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.TicketDAO;
import com.solvd.airport.model.Ticket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    private ConnectionPool pool;

    public TicketDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Ticket findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Tickets where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Ticket ticket = new Ticket();
                Long idParam = rs.getLong("id");
                String ticket_code = rs.getString("ticket_code");
                String seat = rs.getString("seat");
                Long flightId = rs.getLong("flight_id");
                Long passengerId = rs.getLong("passenger_id");
                Long ticketClassId = rs.getLong("ticket_class_id");

                ticket.setId(idParam);
                ticket.setTicketCode(ticket_code);
                ticket.setSeat(seat);
                ticket.setFlightId(flightId);
                ticket.setPassengerId(passengerId);
                ticket.setTicketClassId(ticketClassId);
                pool.releaseConnection(connection);
                return ticket;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(Ticket entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into Tickets(id, ticket_code, seat, flight_id, passenger_id, ticket_class_id) " +
                    "values (" + entity.getId() +", \"" + entity.getTicketCode() +
                    "\", \"" + entity.getSeat() + "\", " + entity.getFlightId() +
                    ", " + entity.getPassengerId() + ", " + entity.getTicketClassId() + ")";




            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Ticket entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Tickets set ticket_code = \"" + entity.getTicketCode() +
                    "\", seat = \"" + entity.getSeat() + "\", flight_id = " + entity.getFlightId() +
                    ", passenger_id = " + entity.getPassengerId() +
                    ", ticket_class_id = " + entity.getTicketClassId() +
                    " where id = " + entity.getId();

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
            String query = "delete from Tickets where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Tickets";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Ticket ticket = new Ticket();
                Long idParam = rs.getLong("id");
                String ticket_code = rs.getString("ticket_code");
                String seat = rs.getString("seat");
                Long flightId = rs.getLong("flight_id");
                Long passengerId = rs.getLong("passenger_id");
                Long ticketClassId = rs.getLong("ticket_class_id");

                ticket.setId(idParam);
                ticket.setTicketCode(ticket_code);
                ticket.setSeat(seat);
                ticket.setFlightId(flightId);
                ticket.setPassengerId(passengerId);
                ticket.setTicketClassId(ticketClassId);

                tickets.add(ticket);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return tickets;
    }
}
