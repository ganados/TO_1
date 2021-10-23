package com.company.currencyexchange.converterinterface;

import java.awt.event.ActionListener;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface CurrencyConverter extends ActionListener {

    void convertCurrencies(final String in, final String out, final String value) throws ParserConfigurationException, SAXException, IOException;

}
