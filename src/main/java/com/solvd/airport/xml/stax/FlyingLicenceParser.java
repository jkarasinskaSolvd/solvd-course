package com.solvd.airport.xml.stax;

import com.solvd.airport.model.FlyingLicence;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.time.LocalDate;

public class FlyingLicenceParser {
    private FlyingLicence flyingLicence;

    public FlyingLicence parseFlyingLicence(XMLEventReader reader) throws XMLStreamException {
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
                    case "flyingLicence":
                        flyingLicence = new FlyingLicence();
                        Attribute id = startElement.getAttributeByName(new QName("id"));
                        if (id != null) {
                            flyingLicence.setId(Long.valueOf(id.getValue()));
                        }
                        break;
                    case "licenceCode":
                        nextEvent = reader.nextEvent();
                        flyingLicence.setLicenceCode(nextEvent.asCharacters().getData());
                        break;
                    case "issueDate":
                        nextEvent = reader.nextEvent();
                        flyingLicence.setIssueDate(LocalDate.parse(nextEvent.asCharacters().getData()));
                        break;
                    case "expirationDate":
                        nextEvent = reader.nextEvent();
                        flyingLicence.setExpirationDate(LocalDate.parse(nextEvent.asCharacters().getData()));
                        break;
                    case "pilotId":
                        nextEvent = reader.nextEvent();
                        flyingLicence.setPilotId(Long.valueOf(nextEvent.asCharacters().getData()));
                        break;
                }
            }
        }
        return flyingLicence;
    }
}
