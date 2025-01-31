package com.solvd.airport.xml.validation;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class GeneralValidator {
    private  Validator initValidator(String xsdPath) throws SAXException, URISyntaxException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(getFile(xsdPath));
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }

    private  File getFile(String location) throws URISyntaxException {
        return new File(String.valueOf(Path.of("src\\main\\java\\com\\solvd\\airport\\xml\\"+location)));
    }

    public boolean isValid(String xsdPath, String xmlPath) throws IOException, SAXException, URISyntaxException {
        Validator validator = initValidator(xsdPath);
        try {
            validator.validate(new StreamSource(getFile(xmlPath)));
            return true;
        } catch (SAXException e) {
            return false;
        }
    }




}
