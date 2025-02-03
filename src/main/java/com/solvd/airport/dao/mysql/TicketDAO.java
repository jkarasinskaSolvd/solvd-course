package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.ITicketDAO;
import com.solvd.airport.model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements ITicketDAO {
    private ConnectionPool pool;

    public TicketDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Ticket findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from ticket where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();
            if (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getLong("id"));
                ticket.setTicketCode(rs.getString("ticket_code"));
                ticket.setSeat(rs.getString("seat"));
                ticket.setFlightId(rs.getLong("flight_id"));
                ticket.setPassengerId(rs.getLong("passenger_id"));
                ticket.setTicketClassId(rs.getLong("ticket_class_id"));
                return ticket;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean save(Ticket entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement(
                    "insert into Tickets(id, ticket_code, seat, flight_id, passenger_id, ticket_class_id) " +
                            "values (?,?,?,?,?,?)");
            prstmt.setLong(1, entity.getId());
            prstmt.setString(2, entity.getTicketCode());
            prstmt.setString(3, entity.getSeat());
            prstmt.setLong(4, entity.getFlightId());
            prstmt.setLong(5, entity.getPassengerId());
            prstmt.setLong(6, entity.getTicketClassId());
            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(Ticket entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement(
                    "update Tickets set ticket_code = ?, seat = ?, flight_id = ?, passenger_id = ?" +
                            ", ticket_class_id = ? where id = ?");
            prstmt.setString(1, entity.getTicketCode());
            prstmt.setString(2, entity.getSeat());
            prstmt.setLong(3, entity.getFlightId());
            prstmt.setLong(4, entity.getPassengerId());
            prstmt.setLong(5, entity.getTicketClassId());
            prstmt.setLong(6, entity.getId());
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Tickets where id = ?");
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
    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Tickets");
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getLong("id"));
                ticket.setTicketCode(rs.getString("ticket_code"));
                ticket.setSeat(rs.getString("seat"));
                ticket.setFlightId(rs.getLong("flight_id"));
                ticket.setPassengerId(rs.getLong("passenger_id"));
                ticket.setTicketClassId(rs.getLong("ticket_class_id"));
                tickets.add(ticket);
            }

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return tickets;
    }
}
