package com.company.currencyexchange.converterinterface;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface CurrencyConverter {

    void convertCurrencies(final String in, final String out, final String value) throws ParserConfigurationException, SAXException, IOException;
}
