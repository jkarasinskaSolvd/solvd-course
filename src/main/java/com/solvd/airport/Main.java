package com.solvd.airport;

import com.solvd.airport.model.*;
import com.solvd.airport.xml.sax.*;
import com.solvd.airport.xml.stax.*;
import com.solvd.airport.xml.validation.GeneralValidator;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
//        try {
//            DatabaseSourceConfig databaseSourceConfig = new DatabaseSourceConfig("jdbc:mysql://localhost:3306/mydb", "root", "asia");
//            ConnectionPool pool = ConnectionPool.getInstance(5, databaseSourceConfig);
//            CountryDAO countryDAO = new CountryDAOImpl(pool);
//
//            FlightDAO flightDAO = new FlightDAOImpl(pool);
//            FlyingLicenceDAO flyingLicenceDAO = new FlyingLicenceDAOImpl(pool);
//
//            Country country1 = new Country(2L,"Germany","GER");
//            countryDAO.save(country1);
//
//            Country country2 = countryDAO.findByID(2L);
//            System.out.println(country2);
//
//            country2.setCode("GE");
//            countryDAO.update(country2);
//
//            country2 = countryDAO.findByID(2L);
//            System.out.println(country2);
//
//            countryDAO.delete(2L);
//
//            List<Country> countries = countryDAO.findAll();
//            for (Country country : countries) {
//                System.out.println(country);
//            }
//
//            Flight flight = flightDAO.findByID(1L);
//
//            System.out.println(flight);
//            flight.setStartTime(LocalDateTime.now());
//            flight.setSecondPilotId(null);
//            flightDAO.update(flight);
//
//            flight.setId(2L);
//            flightDAO.save(flight);
//
//            flightDAO.delete(2L);
//
//            FlyingLicence flyingLicence = flyingLicenceDAO.findByID(1L);
//            flyingLicence.setIssueDate(LocalDate.now());
//            flyingLicence.setLicenceCode("111111111");
//            flyingLicenceDAO.update(flyingLicence);
//            flyingLicence.setLicenceCode("11111");
//            flyingLicence.setId(2L);
//            flyingLicenceDAO.save(flyingLicence);
//
//            flyingLicenceDAO.delete(2L);
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        System.out.println("\nXSD SCHEMA VALIDATION\n");
        GeneralValidator validator = new GeneralValidator();
        try {
            System.out.println("Address validation result: "+validator.isValid("Address.xsd","Address.xml"));
            System.out.println("Airport validation result: "+validator.isValid("Airport.xsd","Airport.xml"));
            System.out.println("Country validation result: "+validator.isValid("Country.xsd","Country.xml"));
            System.out.println("Flying licence validation result: "+validator.isValid("FlyingLicence.xsd","FlyingLicence.xml"));
            System.out.println("Hangar validation result: "+validator.isValid("Hangar.xsd","Hangar.xml"));
            System.out.println("Person validation result: "+validator.isValid("Person.xsd","Person.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nPARSING WITH STAX\n");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        CountryHandler countryHandler = new CountryHandler();
        AddressHandler addressHandler = new AddressHandler();
        AirportHandler airportHandler = new AirportHandler();
        FlyingLicenceHandler flyingLicenceHandler = new FlyingLicenceHandler();
        HangarHandler hangarHandler = new HangarHandler();
        PersonHandler personHandler = new PersonHandler();

        try {
            saxParser.parse("src/main/java/com/solvd/airport/xml/Country.xml", countryHandler);
            Country country = countryHandler.getCountry();
            System.out.println(country);

            saxParser.parse("src/main/java/com/solvd/airport/xml/Address.xml", addressHandler);
            Address address = addressHandler.getAddress();
            System.out.println(address);

            saxParser.parse("src/main/java/com/solvd/airport/xml/Airport.xml", airportHandler);
            Airport airport = airportHandler.getAirport();
            System.out.println(airport);

            saxParser.parse("src/main/java/com/solvd/airport/xml/FlyingLicence.xml", flyingLicenceHandler);
            FlyingLicence flyingLicence = flyingLicenceHandler.getFlyingLicence();
            System.out.println(flyingLicence);

            saxParser.parse("src/main/java/com/solvd/airport/xml/Hangar.xml", hangarHandler);
            Hangar hangar = hangarHandler.getHangar();
            System.out.println(hangar);

            saxParser.parse("src/main/java/com/solvd/airport/xml/Person.xml", personHandler);
            Person person = personHandler.getPerson();
            System.out.println(person);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nPARSING WITH STAX\n");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/java/com/solvd/airport/xml/Country.xml"));
            CountryParser countryParse = new CountryParser();
            System.out.println(countryParse.parseCountry(reader));

            reader = xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/java/com/solvd/airport/xml/Address.xml"));
            AddressParser addressParser = new AddressParser();
            System.out.println(addressParser.parseAddress(reader));

            reader = xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/java/com/solvd/airport/xml/Airport.xml"));
            AirportParser airportParser = new AirportParser();
            System.out.println(airportParser.parseAirport(reader));

            reader = xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/java/com/solvd/airport/xml/FlyingLicence.xml"));
            FlyingLicenceParser flyingLicenceParser = new FlyingLicenceParser();
            System.out.println(flyingLicenceParser.parseFlyingLicence(reader));

            reader = xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/java/com/solvd/airport/xml/Hangar.xml"));
            HangarParser hangarParser = new HangarParser();
            System.out.println(hangarParser.parseHangar(reader));

            reader = xmlInputFactory.createXMLEventReader(new FileInputStream("src/main/java/com/solvd/airport/xml/Person.xml"));
            PersonParser personParser = new PersonParser();
            System.out.println(personParser.parseHangar(reader));

        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
