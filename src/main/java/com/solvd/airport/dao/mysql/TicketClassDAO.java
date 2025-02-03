package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.ITicketClassDAO;
import com.solvd.airport.model.TicketClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketClassDAO implements ITicketClassDAO {
    private ConnectionPool pool;

    public TicketClassDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public TicketClass findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Ticket_Classes where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                TicketClass ticketClass = new TicketClass();
                ticketClass.setId(rs.getLong("id"));
                ticketClass.setName(rs.getString("name"));

                return ticketClass;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean save(TicketClass entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement(
                    "insert into Ticket_Classes(id, name) values(?, ?)");
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
    public Boolean update(TicketClass entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement(
                    "update Ticket_Classes set name = ? where id = ?");
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Ticket_Classes where id = ?");
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
    public List<TicketClass> findAll() {
        List<TicketClass> ticketClasses = new ArrayList<TicketClass>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Ticket_Classes");
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()) {
                TicketClass ticketClass = new TicketClass();
                ticketClass.setId(rs.getLong("id"));
                ticketClass.setName(rs.getString("name"));
                ticketClasses.add(ticketClass);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return ticketClasses;
    }
}
