package com.solvd.airport.xml.sax;

import com.solvd.airport.model.Country;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Objects;

public class CountryHandler extends DefaultHandler {
    private Country country;
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
        country = new Country();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = new StringBuilder();
        if(Objects.nonNull(attributes.getValue("id"))){
            country.setId(Long.valueOf(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "name":
                country.setName(elementValue.toString());
                break;
            case "code":
                country.setCode(elementValue.toString());
                break;
        }
    }

    public Country getCountry() {
        return country;
    }
}
