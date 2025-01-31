package com.solvd.airport.xml.sax;

import com.solvd.airport.model.Address;
import com.solvd.airport.model.Country;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Objects;

public class AddressHandler extends DefaultHandler {
    private Address address;
    private StringBuilder elementValue;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        address = new Address();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = new StringBuilder();
        if(Objects.nonNull(attributes.getValue("id"))){
            address.setId(Long.valueOf(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "city":
                address.setCity(elementValue.toString());
                break;
            case "postCode":
                address.setPostCode(elementValue.toString());
                break;
            case "street":
                address.setStreet(elementValue.toString());
                break;
            case "streetNumber":
                address.setStreetNumber(elementValue.toString());
                break;
            case "apartmentNumber":
                address.setApartmentNumber(elementValue.toString());
                break;
            case "countryId":
                address.setCountryId(Long.valueOf(elementValue.toString()));
                break;
        }
    }

    public Address getAddress() {
        return address;
    }
}
