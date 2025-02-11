package com.solvd.airport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.airport.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JSONMain {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        
        
        //Initialization of models
        Address address = new Address(
                100L,
                "Wroclaw",
                "90-210",
                "Polna",
                "12a",
                "10",
                1L
        );
        
        Airline airline = new Airline(
                100L,
                "Flying",
                1L
        );
        
        Airport airport = new Airport(
                100L,
                "Airport First",
                "Aif",
                100L
        );
        
        Country country = new Country(
                1L,
                "Poland",
                "PL"
        );

        Flight flight = new Flight(
                100L,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(10),
                2L,
                2L,
                1L,
                1L,
                3L
        );
        
        FlyingLicence flyingLicence = new FlyingLicence(
                100L,
                "lic1",
                LocalDate.now(),
                LocalDate.now().plusYears(1L),
                100L
        );
        
        Hangar hangar = new Hangar(
              100L,
              "han1",
              10,
              100L  
        );
        
        Person person = new Person(
                100L,
                "Jan",
                "Pan",
                "Kowalski",
                100L
        );
        
        Plane plane = new Plane(
                100L,
                "pl001",
                100,
                2001,
                100L,
                100L,
                100L
        );
        
        PlaneType planeType = new PlaneType(
                100L,
                "Passenger"
        );
        
        Ticket ticket = new Ticket(
                100L,
                "tc-01",
                "10a",
                100L,
                100L,
                100L
        );
        
        TicketClass ticketClass = new TicketClass(
                100L,
                "Business"
        );
        
        try {
            Path basePath = Path.of("target/json");
            if(Files.notExists(basePath)){
                Files.createDirectory(basePath);
            }
            //Address
            File addressFile = new File(basePath+"/address.json");
            objectMapper.writeValue(addressFile,address);
            Address addressRead = objectMapper.readValue(addressFile,Address.class);
            System.out.println(addressRead);

            //Airline
            File airlineFile = new File(basePath+"/airline.json");
            objectMapper.writeValue(airlineFile,airline);
            Airline airlineRead = objectMapper.readValue(airlineFile,Airline.class);
            System.out.println(airlineRead);
            
            //Airport
            File airportFile = new File(basePath+"/airport.json");
            objectMapper.writeValue(airportFile, airport);
            Airport airportRead = objectMapper.readValue(airportFile,Airport.class);
            System.out.println(airportRead);
            
            //Country
            File countryFile = new File(basePath+"/country.json");
            objectMapper.writeValue(countryFile,country);
            Country countryRead = objectMapper.readValue(countryFile,Country.class);
            System.out.println(countryRead);
            
            //Flight
            File flightFile = new File(basePath+"/flight.json");
            objectMapper.writeValue(flightFile,flight);
            Flight flightRead = objectMapper.readValue(flightFile,Flight.class);
            System.out.println(flightRead);

            //FlyingLicence
            File flyingLicenceFile = new File(basePath+"/flying-licence.json");
            objectMapper.writeValue(flyingLicenceFile,flyingLicence);
            FlyingLicence flyingLicenceRead = objectMapper.readValue(flyingLicenceFile, FlyingLicence.class);
            System.out.println(flyingLicenceRead);
            
            //Hangar
            File hangarFile = new File(basePath+"/hangar.json");
            objectMapper.writeValue(hangarFile, hangar);
            Hangar readHangar = objectMapper.readValue(hangarFile, Hangar.class);
            System.out.println(readHangar);
            
            //Person
            File personFile = new File(basePath+"/person.json");
            objectMapper.writeValue(personFile, person);
            Person readPerson = objectMapper.readValue(personFile, Person.class);
            System.out.println(readPerson);
            
            //Plane
            File planeFile = new File(basePath+"/plane.json");
            objectMapper.writeValue(planeFile, plane);
            Plane readPlane = objectMapper.readValue(planeFile, Plane.class);
            System.out.println(readPlane);
            
            //PlaneType
            File planeTypeFile = new File(basePath+"/plane-type.json");
            objectMapper.writeValue(planeTypeFile, planeType);
            PlaneType planeTypeRead = objectMapper.readValue(planeTypeFile, PlaneType.class);
            System.out.println(planeTypeRead);
            
            //Ticket
            File ticketFile = new File(basePath+"/ticket.json");
            objectMapper.writeValue(ticketFile, ticket);
            Ticket readTicket = objectMapper.readValue(ticketFile, Ticket.class);
            System.out.println(readTicket);

            //TicketClass
            File ticketClassFile = new File(basePath+"/ticket-class.json");
            objectMapper.writeValue(ticketClassFile, ticketClass);
            TicketClass readTicketClass = objectMapper.readValue(ticketClassFile, TicketClass.class);
            System.out.println(readTicketClass);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
