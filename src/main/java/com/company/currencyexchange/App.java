package com.company.currencyexchange;

import com.company.currencyexchange.domain.Currencies;
import com.company.currencyexchange.providers.currency.CurrenciesProvider;
import com.company.currencyexchange.controller.CurrencyExchangeController;
import com.company.currencyexchange.view.CurrencyExchangeViewer;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class App {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        CurrenciesProvider currenciesProvider = new CurrenciesProvider();
        Currencies currencies = currenciesProvider.createCurrencies();
        CurrencyExchangeViewer currencyExchangeViewer = new CurrencyExchangeViewer();
        CurrencyExchangeController currencyExchangeController = new CurrencyExchangeController(currenciesProvider, currencyExchangeViewer);

        currencyExchangeViewer.createGUI(currencies);
    }
}
