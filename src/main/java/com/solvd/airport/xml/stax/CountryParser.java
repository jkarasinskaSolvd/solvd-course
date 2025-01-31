package com.solvd.airport.xml.stax;

import com.solvd.airport.model.Country;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class CountryParser {

    private Country country;
    public Country parseCountry(XMLEventReader reader) throws XMLStreamException {
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
                    case "country":
                        country = new Country();
                        Attribute id = startElement.getAttributeByName(new QName("id"));
                        if (id != null) {
                            country.setId(Long.valueOf(id.getValue()));
                        }
                        break;
                    case "name":
                        nextEvent = reader.nextEvent();
                        country.setName(nextEvent.asCharacters().getData());
                        break;
                    case "code":
                        nextEvent = reader.nextEvent();
                        country.setCode(nextEvent.asCharacters().getData());
                        break;
                }
            }
        }
        return country;
    }
}
