package main;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, SAXException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean check = true;
        Double amount = 0.0;
        String amountString;
        System.out.println("Hello!");
        System.out.println("Welcome to the currency calculator app.");
        while (check) {
            System.out.println("Write the currency name in this format - PLN:");
            String currency = reader.readLine();
            System.out.println("Write the amount in EUR:");
            amountString = reader.readLine();
            if (!amountString.isEmpty() && !currency.isEmpty()) {
                amount = Double.parseDouble(amountString);
                getAmount(currency, amount);
            } else {
                System.out.println("Wrong data given.");
            }
            System.out.println("Do you want to make another calculation? (Y/N)");
            if (reader.readLine().equals("N")) {
                check = false;
                System.out.println("Goodbye");
            }
        }
    }

    public static void getAmount(String currencyName, Double eurAmount) throws IOException, SAXException {
        Parser parser = new Parser();

        ArrayList<Currency> currencies = parser.parse();

        for (Currency currency : currencies) {
            if (currencyName.equals(currency.getName())) {
                double result = (eurAmount * currency.getRate());
                System.out.println("Calculated value: " + result);
                return;
            }
        }
        System.out.println("Wrong data put in.");
    }
}
