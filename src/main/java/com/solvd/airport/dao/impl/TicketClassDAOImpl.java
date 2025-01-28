package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.TicketClassDAO;
import com.solvd.airport.model.TicketClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketClassDAOImpl implements TicketClassDAO {
    private ConnectionPool pool;

    public TicketClassDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public TicketClass findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Ticket_Classes where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                TicketClass ticketClass = new TicketClass();
                Long idParam = rs.getLong("id");
                String name = rs.getString("name");

                ticketClass.setId(idParam);
                ticketClass.setName(name);

                pool.releaseConnection(connection);
                return ticketClass;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(TicketClass entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into Ticket_Classes(id, name) values (" + entity.getId() +", \""
                    + entity.getName() + "\")";

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(TicketClass entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Ticket_Classes set name = \"" + entity.getName()
                    + "\" where id = " + entity.getId();

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
            String query = "delete from Ticket_Classes where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TicketClass> findAll() {
        List<TicketClass> ticketClasses = new ArrayList<TicketClass>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Ticket_Classes";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                TicketClass ticketClass = new TicketClass();
                Long idParam = rs.getLong("id");
                String name = rs.getString("name");

                ticketClass.setId(idParam);
                ticketClass.setName(name);

                ticketClasses.add(ticketClass);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return ticketClasses;
    }
}
