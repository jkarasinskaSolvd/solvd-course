package com.solvd.airport.xml.stax;

import com.solvd.airport.model.Hangar;
import com.solvd.airport.model.Person;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class PersonParser {

    private Person person;
    public Person parseHangar(XMLEventReader reader) throws XMLStreamException {
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
                    case "person":
                        person = new Person();
                        Attribute id = startElement.getAttributeByName(new QName("id"));
                        if (id != null) {
                            person.setId(Long.valueOf(id.getValue()));
                        }
                        break;
                    case "firstName":
                        nextEvent = reader.nextEvent();
                        person.setFirstName(nextEvent.asCharacters().getData());
                        break;
                    case "secondName":
                        nextEvent = reader.nextEvent();
                        person.setSecondName(nextEvent.asCharacters().getData());
                        break;
                    case "lastName":
                        nextEvent = reader.nextEvent();
                        person.setLastName(nextEvent.asCharacters().getData());
                        break;
                    case "addressId":
                        nextEvent = reader.nextEvent();
                        person.setAddressId(Long.valueOf(nextEvent.asCharacters().getData()));
                        break;
                }
            }
        }
        return person;
    }

}
