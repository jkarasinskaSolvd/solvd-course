package com.solvd.airport.xml.stax;

import com.solvd.airport.model.Address;
import com.solvd.airport.model.Country;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class AddressParser {
    private Address address;
    public Address parseAddress(XMLEventReader reader) throws XMLStreamException {
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
                    case "address":
                        address = new Address();
                        Attribute id = startElement.getAttributeByName(new QName("id"));
                        if (id != null) {
                            address.setId(Long.valueOf(id.getValue()));
                        }
                        break;
                    case "city":
                        nextEvent = reader.nextEvent();
                        address.setCity(nextEvent.asCharacters().getData());
                        break;
                    case "postCode":
                        nextEvent = reader.nextEvent();
                        address.setPostCode(nextEvent.asCharacters().toString());
                        break;
                    case "street":
                        nextEvent = reader.nextEvent();
                        address.setStreet(nextEvent.asCharacters().toString());
                        break;
                    case "streetNumber":
                        nextEvent = reader.nextEvent();
                        address.setStreetNumber(nextEvent.asCharacters().toString());
                        break;
                    case "apartmentNumber":
                        nextEvent = reader.nextEvent();
                        address.setApartmentNumber(nextEvent.asCharacters().toString());
                        break;
                    case "countryId":
                        nextEvent = reader.nextEvent();
                        address.setCountryId(Long.valueOf(nextEvent.asCharacters().toString()));
                        break;
                }
            }
        }
        return address;

    }
}
