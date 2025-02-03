package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.IFlyingLicenceDAO;
import com.solvd.airport.model.FlyingLicence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlyingLicenceDAO implements IFlyingLicenceDAO {
    private ConnectionPool pool;

    public FlyingLicenceDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public FlyingLicence findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Flying_Licences where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();
            if (rs.next()) {
                FlyingLicence flyingLicence = new FlyingLicence();
                flyingLicence.setId(rs.getLong("id"));
                flyingLicence.setLicenceCode(rs.getString("licence_code"));
                flyingLicence.setIssueDate(rs.getDate("issue_date").toLocalDate());
                flyingLicence.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
                flyingLicence.setPilotId(rs.getLong("pilot_id"));
                return flyingLicence;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean save(FlyingLicence entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("insert into " +
                    "Flying_Licences(id, licence_code, issue_date, expiration_date, pilot_id) " +
                    "values(?,?,?,?,?)");
            prstmt.setLong(1, entity.getId());
            prstmt.setString(2, entity.getLicenceCode());
            prstmt.setDate(3,Date.valueOf(entity.getIssueDate()));
            prstmt.setDate(4,Date.valueOf(entity.getExpirationDate()));
            prstmt.setLong(5, entity.getPilotId());
            prstmt.executeUpdate();

            return true;
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);

        }
    }

    @Override
    public Boolean update(FlyingLicence entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("update Flying_Licences set licence_code = ?" +
                    ", issue_date = ?, expiration_date = ?, pilot_id = ? where id = ?");
            prstmt.setString(1, entity.getLicenceCode());
            prstmt.setDate(2, Date.valueOf(entity.getIssueDate()));
            prstmt.setDate(3, Date.valueOf(entity.getExpirationDate()));
            prstmt.setLong(4, entity.getPilotId());
            prstmt.setLong(5, entity.getId());
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
            PreparedStatement prstmt = connection.prepareStatement("delete from Flying_Licences where id = ?");
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
    public List<FlyingLicence> findAll() {
        List<FlyingLicence> flyingLicences = new ArrayList<FlyingLicence>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from Countries");
            ResultSet rs = prstmt.executeQuery();

            while (rs.next()) {
                FlyingLicence flyingLicence = new FlyingLicence();
                flyingLicence.setId(rs.getLong("id"));
                flyingLicence.setLicenceCode(rs.getString("licence_code"));
                flyingLicence.setIssueDate(rs.getDate("issue_date").toLocalDate());
                flyingLicence.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
                flyingLicence.setPilotId(rs.getLong("pilot_id"));
                flyingLicences.add(flyingLicence);
            }


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return flyingLicences;
    }
}
