package com.solvd.airport.xml.sax;

import com.solvd.airport.model.FlyingLicence;
import com.solvd.airport.model.Hangar;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.Objects;

public class HangarHandler extends DefaultHandler {
    private Hangar hangar;
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
        hangar = new Hangar();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = new StringBuilder();
        if(Objects.nonNull(attributes.getValue("id"))){
            hangar.setId(Long.valueOf(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "name":
                hangar.setName(elementValue.toString());
                break;
            case "capacity":
                hangar.setCapacity(Integer.valueOf(elementValue.toString()));
                break;
            case "airportId":
                hangar.setAirportId(Long.valueOf(elementValue.toString()));
                break;
        }
    }

    public Hangar getHangar() {
        return hangar;
    }
}
