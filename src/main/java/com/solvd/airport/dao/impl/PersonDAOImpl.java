package com.solvd.airport.dao.impl;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.dao.PersonDAO;
import com.solvd.airport.model.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    private ConnectionPool pool;

    public PersonDAOImpl (ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Person findByID(Long id) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from People where id = " + id;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                Person person = new Person();
                Long idParam = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String secondName = rs.getString("second_name");
                String lastName = rs.getString("last_name");
                Long addressId = rs.getLong("address_id");

                person.setId(idParam);
                person.setFirstName(firstName);
                person.setSecondName(secondName);
                person.setLastName(lastName);
                person.setAddressId(addressId);

                pool.releaseConnection(connection);
                return person;
            }
            pool.releaseConnection(connection);
            return null;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean save(Person entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into People(id, first_name, second_name, last_name, address_id) values ("
                    + entity.getId() + ", \"" + entity.getFirstName() + "\", \"" + entity.getSecondName() + "\", \""
                    + entity.getLastName() + "\", \"" + entity.getAddressId() + "\")";


            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean update(Person entity) {
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "update People set first_name = \"" + entity.getFirstName() + "\", second_name = \""
                    + entity.getSecondName() + "\", last_name = \"" + entity.getLastName() + "\", address_id = "
                    + entity.getAddressId() + " where id = " + entity.getId();

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
            String query = "delete from People where id = " + id;

            statement.executeUpdate(query);
            pool.releaseConnection(connection);
            return true;

        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> findAll() {
        List<Person> people = new ArrayList<Person>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from Countries";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Person person = new Person();
                Long idParam = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String secondName = rs.getString("second_name");
                String lastName = rs.getString("last_name");
                Long addressId = rs.getLong("address_id");

                person.setId(idParam);
                person.setFirstName(firstName);
                person.setSecondName(secondName);
                person.setLastName(lastName);
                person.setAddressId(addressId);

                people.add(person);
            }
            pool.releaseConnection(connection);


        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }
}
