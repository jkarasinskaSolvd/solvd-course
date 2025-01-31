package com.solvd.airport.xml.stax;

import com.solvd.airport.model.Hangar;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class HangarParser {

    private Hangar hangar;
    public Hangar parseHangar(XMLEventReader reader) throws XMLStreamException {
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
                    case "hangar":
                        hangar = new Hangar();
                        Attribute id = startElement.getAttributeByName(new QName("id"));
                        if (id != null) {
                            hangar.setId(Long.valueOf(id.getValue()));
                        }
                        break;
                    case "name":
                        nextEvent = reader.nextEvent();
                        hangar.setName(nextEvent.asCharacters().getData());
                        break;
                    case "capacity":
                        nextEvent = reader.nextEvent();
                        hangar.setCapacity(Integer.valueOf(nextEvent.asCharacters().getData()));
                        break;
                    case "airportId":
                        nextEvent = reader.nextEvent();
                        hangar.setAirportId(Long.valueOf(nextEvent.asCharacters().getData()));
                        break;
                }
            }
        }
        return hangar;
    }
}
