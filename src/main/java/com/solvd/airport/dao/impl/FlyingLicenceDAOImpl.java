package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.FlyingLicenceDAO;
import com.solvd.airport.model.FlyingLicence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlyingLicenceDAOImpl implements FlyingLicenceDAO {
    private ConnectionPool pool;

    public FlyingLicenceDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public FlyingLicence findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Flying_Licences where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                FlyingLicence flyingLicence = new FlyingLicence();
                Long idParam = rs.getLong("id");
                String licenceCode = rs.getString("licence_code");
                LocalDate issueDate = rs.getDate("issue_date").toLocalDate();
                LocalDate expirationDate = rs.getDate("expiration_date").toLocalDate();
                Long pilotId = rs.getLong("pilot_id");

                flyingLicence.setId(idParam);
                flyingLicence.setLicenceCode(licenceCode);
                flyingLicence.setIssueDate(issueDate);
                flyingLicence.setExpirationDate(expirationDate);
                flyingLicence.setPilotId(pilotId);
                pool.releaseConnection(connection);
                return flyingLicence;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(FlyingLicence entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into Flying_Licences(id, licence_code, issue_date, expiration_date, pilot_id) " +
                    "values (" + entity.getId() +", \"" + entity.getLicenceCode() + "\", \"" +
                    entity.getIssueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\", \"" + entity.getExpirationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\", " +
                    entity.getPilotId() + ")";

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(FlyingLicence entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update Flying_Licences set licence_code = \"" + entity.getLicenceCode() +
                    "\", issue_date = \"" + entity.getIssueDate() +
                    "\", expiration_date = \"" + entity.getExpirationDate() +
                    "\", pilot_id = \"" + entity.getPilotId() +
                    "\" where id = " + entity.getId();

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
            String query = "delete from Flying_Licences where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FlyingLicence> findAll() {
        List<FlyingLicence> flyingLicences = new ArrayList<FlyingLicence>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Countries";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                FlyingLicence flyingLicence = new FlyingLicence();
                Long idParam = rs.getLong("id");
                String licenceCode = rs.getString("licence_code");
                LocalDate issueDate = rs.getDate("issue_date").toLocalDate();
                LocalDate expirationDate = rs.getDate("expiration_date").toLocalDate();
                Long pilotId = rs.getLong("pilot_id");

                flyingLicence.setId(idParam);
                flyingLicence.setLicenceCode(licenceCode);
                flyingLicence.setIssueDate(issueDate);
                flyingLicence.setExpirationDate(expirationDate);
                flyingLicence.setPilotId(pilotId);
                flyingLicences.add(flyingLicence);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return flyingLicences;
    }
}
