package com.solvd.airport.xml.sax;

import com.solvd.airport.model.Address;
import com.solvd.airport.model.Airport;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Objects;

public class AirportHandler extends DefaultHandler {
    private Airport airport;
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
        airport = new Airport();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = new StringBuilder();
        if(Objects.nonNull(attributes.getValue("id"))){
            airport.setId(Long.valueOf(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "name":
                airport.setName(elementValue.toString());
                break;
            case "code":
                airport.setCode(elementValue.toString());
                break;
            case "addressId":
                airport.setAddressId(Long.valueOf(elementValue.toString()));
                break;
        }
    }

    public Airport getAirport() {
        return airport;
    }
}
