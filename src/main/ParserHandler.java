package main;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Stack;

public class ParserHandler extends DefaultHandler {
    private ArrayList currencies = new ArrayList<>();

    private Stack elementStack = new Stack();

    private Stack objectStack = new Stack();

    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        this.elementStack.push(qName);

        if ("Cube".equals(qName)) {

            Currency currency = new Currency();

            if (attributes != null && attributes.getLength() == 2) {
                currency.setName(attributes.getValue("currency"));
                currency.setRate(Double.parseDouble(attributes.getValue("rate")));
            }
            this.objectStack.push(currency);
        }
    }

    public void endElement(String uri, String localName, String qName) {
        this.elementStack.pop();

        if ("Cube".equals(qName)) {
            Currency currency = (Currency) this.objectStack.pop();
            this.currencies.add(currency);
        }
    }

    public ArrayList getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList currencies) {
        this.currencies = currencies;
    }
}
