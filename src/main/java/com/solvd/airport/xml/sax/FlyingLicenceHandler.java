package com.solvd.airport.xml.sax;

import com.solvd.airport.model.Address;
import com.solvd.airport.model.FlyingLicence;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.Objects;

public class FlyingLicenceHandler extends DefaultHandler {
    private FlyingLicence flyingLicence;
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
        flyingLicence = new FlyingLicence();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = new StringBuilder();
        if(Objects.nonNull(attributes.getValue("id"))){
            flyingLicence.setId(Long.valueOf(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "licenceCode":
                flyingLicence.setLicenceCode(elementValue.toString());
                break;
            case "issueDate":
                flyingLicence.setIssueDate(LocalDate.parse(elementValue.toString()));
                break;
            case "expirationDate":
                flyingLicence.setExpirationDate(LocalDate.parse(elementValue.toString()));
                break;
            case "pilotId":
                flyingLicence.setPilotId(Long.valueOf(elementValue.toString()));
        }
    }

    public FlyingLicence getFlyingLicence() {
        return flyingLicence;
    }
}
