package main;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    private static final String fileName = "eurofxref-daily.xml";

    public ArrayList parse() throws SAXException, IOException {
        ArrayList<Currency> currencies = new ArrayList<>();

        ParserHandler parserHandler = new ParserHandler();

        XMLReader parser = XMLReaderFactory.createXMLReader();

        parser.setContentHandler(parserHandler);

        File file = new File(fileName);

        InputSource input = new InputSource(new FileInputStream(file));

        parser.parse(input);

        currencies = parserHandler.getCurrencies();

        return currencies;
    }
}
