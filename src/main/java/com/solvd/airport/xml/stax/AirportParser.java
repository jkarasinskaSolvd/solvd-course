package com.solvd.airport.xml.stax;

import com.solvd.airport.model.Address;
import com.solvd.airport.model.Airport;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class AirportParser {
    private Airport airport;

    public Airport parseAirport(XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent nextEvent = null;
            try {
                nextEvent = reader.nextEvent();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case "airport":
                        airport = new Airport();
                        Attribute id = startElement.getAttributeByName(new QName("id"));
                        if (id != null) {
                            airport.setId(Long.valueOf(id.getValue()));
                        }
                        break;
                    case "name":
                        nextEvent = reader.nextEvent();
                        airport.setName(nextEvent.asCharacters().getData());
                        break;
                    case "code":
                        nextEvent = reader.nextEvent();
                        airport.setCode(nextEvent.asCharacters().getData());
                        break;
                    case "airportId":
                        nextEvent = reader.nextEvent();
                        airport.setAddressId(Long.valueOf(nextEvent.asCharacters().getData()));
                        break;
                }
            }
        }
        return airport;

    }
}
