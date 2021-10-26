package com.company.currencyexchange.converterinterface;

import com.company.currencyexchange.domain.Currency;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface CurrencyConverter {

    double convertCurrencies(final Currency currencyIn, final Currency currencyOut, final String value) throws ParserConfigurationException, SAXException, IOException;

}
