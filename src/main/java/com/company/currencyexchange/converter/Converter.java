package com.company.currencyexchange.converter;

import com.company.currencyexchange.controller.exceptions.NonPositiveNumberException;
import com.company.currencyexchange.converterinterface.CurrencyConverter;
import com.company.currencyexchange.domain.Currency;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import lombok.extern.java.Log;

@Log
public class Converter implements CurrencyConverter {

    @Override
    public double convertCurrencies(final Currency currencyIn, final Currency currencyOut, final String value) throws ParserConfigurationException, SAXException, IOException {

        double parsedValue = validateValue(value);

        double convertedCurrency = parsedValue * (currencyIn.getRate() / currencyIn.getConverter()) / (currencyOut.getRate() / currencyOut.getConverter());
        return Math.round(convertedCurrency * 100) / 100.0;
    }

    private double validateValue(final String value) {
        double parsed;
        if (value == null || value.isBlank()) {
            throw new NumberFormatException();
        }
        parsed = Double.parseDouble(value.replace(",", "."));

        if (parsed < 0) {
            throw new NonPositiveNumberException("Number have to be positive");
        }

        return parsed;
    }
}
