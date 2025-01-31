package com.solvd.airport.xml.sax;

import com.solvd.airport.model.Hangar;
import com.solvd.airport.model.Person;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Objects;

public class PersonHandler extends DefaultHandler {
    private Person person;
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
        person = new Person();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = new StringBuilder();
        if(Objects.nonNull(attributes.getValue("id"))){
            person.setId(Long.valueOf(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "firstName":
                person.setFirstName(elementValue.toString());
                break;
            case "secondName":
                person.setSecondName(elementValue.toString());
                break;
            case "lastName":
                person.setLastName(elementValue.toString());
                break;
            case "addressId":
                person.setAddressId(Long.valueOf(elementValue.toString()));
                break;
        }
    }

    public Person getPerson() {
        return person;
    }
}
