package com.solvd.airport.dao.mysql;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.IPersonDAO;
import com.solvd.airport.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements IPersonDAO {
    private ConnectionPool pool;

    public PersonDAO(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Person findByID(Long id) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from People where id = ?");
            prstmt.setLong(1, id);
            ResultSet rs = prstmt.executeQuery();

            if (rs.next()) {
                Person person = new Person();
                person.setId(rs.getLong("id"));
                person.setFirstName(rs.getString("first_name"));
                person.setSecondName(rs.getString("second_name"));
                person.setLastName(rs.getString("last_name"));
                person.setAddressId(rs.getLong("address_id"));
                return person;
            }
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);

        }
    }

    @Override
    public Boolean save(Person entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement(
                    "insert into People(id, first_name, second_name, last_name, address_id) values (?,?,?,?,?)");
            prstmt.setLong(1, entity.getId());
            prstmt.setString(2, entity.getFirstName());
            prstmt.setString(3, entity.getSecondName());
            prstmt.setString(4, entity.getLastName());
            prstmt.setLong(5, entity.getAddressId());
            prstmt.executeUpdate();
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Boolean update(Person entity) {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement(
                    "update People set first_name = ?, second_name = ?, last_name = ?, address_id = ? where id = ?");
            prstmt.setString(1, entity.getFirstName());
            prstmt.setString(2, entity.getSecondName());
            prstmt.setString(3, entity.getLastName());
            prstmt.setLong(4, entity.getAddressId());
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
            PreparedStatement prstmt = connection.prepareStatement("delete from People where id = ?");
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
    public List<Person> findAll() {
        List<Person> people = new ArrayList<Person>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement prstmt = connection.prepareStatement("select * from People");
            ResultSet rs = prstmt.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getLong("id"));
                person.setFirstName(rs.getString("first_name"));
                person.setSecondName(rs.getString("second_name"));
                person.setLastName(rs.getString("last_name"));
                person.setAddressId(rs.getLong("address_id"));
                people.add(person);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            pool.releaseConnection(connection);
        }

        return people;
    }
}
