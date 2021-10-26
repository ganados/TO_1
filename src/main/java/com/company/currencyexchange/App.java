package com.company.currencyexchange;

import com.company.currencyexchange.converter.Converter;
import com.company.currencyexchange.domain.Currencies;
import com.company.currencyexchange.providers.currency.CurrenciesAccessor;
import com.company.currencyexchange.controller.CurrencyExchangeController;
import com.company.currencyexchange.view.CurrencyExchangeViewer;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class App {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        CurrenciesAccessor currenciesAccessor = new CurrenciesAccessor();
        Currencies currencies = currenciesAccessor.createCurrencies();
        CurrencyExchangeViewer currencyExchangeViewer = new CurrencyExchangeViewer();
        Converter converter = new Converter();
        CurrencyExchangeController currencyExchangeController = new CurrencyExchangeController(currenciesAccessor, currencyExchangeViewer, converter);

        currencyExchangeViewer.createGUI(currencies);
    }
}
