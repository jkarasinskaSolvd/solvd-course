package com.solvd.airport;

import com.solvd.airport.model.*;
import com.solvd.airport.xml.sax.*;
import com.solvd.airport.xml.stax.*;
import com.solvd.airport.xml.validation.GeneralValidator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class XMLMain {
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        System.out.println("\nXSD SCHEMA VALIDATION\n");
        GeneralValidator validator = new GeneralValidator();
        try {
            System.out.println("Address validation result: "+validator.isValid("Address.xsd","Address.xml"));
            System.out.println("Airport validation result: "+validator.isValid("Airport.xsd","Airport.xml"));
            System.out.println("Country validation result: "+validator.isValid("Country.xsd","Country.xml"));
            System.out.println("Flying licence validation result: "+validator.isValid("FlyingLicence.xsd","FlyingLicence.xml"));
            System.out.println("Hangar validation result: "+validator.isValid("Hangar.xsd","Hangar.xml"));
            System.out.println("Person validation result: "+validator.isValid("Person.xsd","Person.xml"));
        } catch (IOException | SAXException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nPARSING WITH SAX\n");
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

        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//PARSE XML WITH JAXB
        System.out.println("Parse xml with JAXB");

        Address address = new Address();
        address.setId(555L);
        address.setStreet("AAA");
        address.setCity("BBB");
        address.setPostCode("00-000");
        address.setApartmentNumber("12");
        address.setStreetNumber("90C");
        address.setCountryId(1L);


        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Address.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(address, new File("src/main/java/com/solvd/airport/xml/AddressJAXB.xml"));

            Address addressRead = (Address) context.createUnmarshaller().unmarshal(new FileReader("src/main/java/com/solvd/airport/xml/AddressJAXB.xml"));
            System.out.println(addressRead);
        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Flight flight = new Flight();
        flight.setId(111L);
        flight.setFirstPilotId(1L);
        flight.setSecondPilotId(10L);
        flight.setLandingTime(LocalDateTime.now());
        flight.setPlaneId(5L);
        flight.setDestinationAirportId(4L);
        flight.setStartTime(LocalDateTime.now());
        flight.setStartAirportId(4L);


        try {
            context = JAXBContext.newInstance(Flight.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(flight, new File("src/main/java/com/solvd/airport/xml/FlightJAXB.xml"));

            Flight flightRead = (Flight) context.createUnmarshaller().unmarshal(new FileReader("src/main/java/com/solvd/airport/xml/FlightJAXB.xml"));
            System.out.println(flightRead);
        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        FlyingLicence flyingLicence = new FlyingLicence();
        flyingLicence.setId(23L);
        flyingLicence.setLicenceCode("123dd");
        flyingLicence.setIssueDate(LocalDate.now());
        flyingLicence.setExpirationDate(LocalDate.of(2027,5,12));
        flyingLicence.setPilotId(12L);


        try {
            context = JAXBContext.newInstance(FlyingLicence.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(flyingLicence, new File("src/main/java/com/solvd/airport/xml/FlyingLicenceJAXB.xml"));

            FlyingLicence flyingLicenceRead = (FlyingLicence) context.createUnmarshaller()
                    .unmarshal(new FileReader("src/main/java/com/solvd/airport/xml/FlyingLicenceJAXB.xml"));
            System.out.println(flyingLicenceRead);
        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
