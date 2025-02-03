package com.solvd.airport;

import com.solvd.airport.connection.ConnectionPool;
import com.solvd.airport.connection.DatabaseSourceConfig;
import com.solvd.airport.dao.mysql.CountryDAO;
import com.solvd.airport.dao.mysql.FlightDAO;
import com.solvd.airport.dao.mysql.FlyingLicenceDAO;
import com.solvd.airport.model.Country;
import com.solvd.airport.model.Flight;
import com.solvd.airport.model.FlyingLicence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DbMain {
    public static void main(String[] args) {
                try {
            DatabaseSourceConfig databaseSourceConfig = new DatabaseSourceConfig("jdbc:mysql://localhost:3306/mydb", "root", "asia");
            ConnectionPool pool = ConnectionPool.getInstance(5, databaseSourceConfig);
            CountryDAO countryDAO = new CountryDAO(pool);

            FlightDAO flightDAO = new FlightDAO(pool);
            FlyingLicenceDAO flyingLicenceDAO = new FlyingLicenceDAO(pool);

            Country country1 = new Country(2L,"Germany","GER");
            countryDAO.save(country1);

            Country country2 = countryDAO.findByID(2L);
            System.out.println(country2);

            country2.setCode("GE");
            countryDAO.update(country2);

            country2 = countryDAO.findByID(2L);
            System.out.println(country2);

            countryDAO.delete(2L);

            List<Country> countries = countryDAO.findAll();
            for (Country country : countries) {
                System.out.println(country);
            }

            Flight flight = flightDAO.findByID(1L);

            System.out.println(flight);
            flight.setStartTime(LocalDateTime.now());
            flight.setSecondPilotId(null);
            flightDAO.update(flight);

            flight.setId(2L);
            flightDAO.save(flight);

            flightDAO.delete(2L);

            FlyingLicence flyingLicence = flyingLicenceDAO.findByID(1L);
            flyingLicence.setIssueDate(LocalDate.now());
            flyingLicence.setLicenceCode("111111111");
            flyingLicenceDAO.update(flyingLicence);
            flyingLicence.setLicenceCode("11111");
            flyingLicence.setId(2L);
            flyingLicenceDAO.save(flyingLicence);

            flyingLicenceDAO.delete(2L);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
