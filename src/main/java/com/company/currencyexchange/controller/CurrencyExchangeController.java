package com.company.currencyexchange.controller;

import com.company.currencyexchange.controller.exceptions.CurrencyNotFoundException;
import com.company.currencyexchange.controller.exceptions.NonPositiveNumberException;
import com.company.currencyexchange.converter.Converter;
import com.company.currencyexchange.providers.currency.CurrenciesAccessor;
import com.company.currencyexchange.view.CurrencyExchangeViewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import lombok.extern.java.Log;

@Log
public class CurrencyExchangeController implements ActionListener {
    private final CurrenciesAccessor currenciesAccessor;
    private final CurrencyExchangeViewer currencyExchangeViewer;
    private final Converter converter;

    public CurrencyExchangeController(final CurrenciesAccessor currenciesAccessor, final CurrencyExchangeViewer currencyExchangeViewer,
                                      final Converter converter) {
        this.currenciesAccessor = currenciesAccessor;
        this.currencyExchangeViewer = currencyExchangeViewer;
        this.converter = converter;
        currencyExchangeViewer.addActionListener(this);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            String in = currencyExchangeViewer.getCurrencyIn();
            String out = currencyExchangeViewer.getCurrencyOut();
            double result = converter.convertCurrencies(currenciesAccessor.getCurrency(in),
                                                        currenciesAccessor.getCurrency(out),
                                                        currencyExchangeViewer.getValue()
                                                        );
            currencyExchangeViewer.setTextFieldOut(String.valueOf(result));
        } catch (CurrencyNotFoundException currencyNotFoundException) {
            log("Currency not found");
            log.warning("Currency not found");
        } catch (ParserConfigurationException | IOException | SAXException parserConfigurationException) {
            log("Parsing file failed");
            log.warning("Parsing file failed");
        } catch (NumberFormatException numberFormatException){
            log("Wrong value entered");
            log.warning("Wrong value entered");
        } catch (NonPositiveNumberException nonPositiveNumberException) {
            log("Number have to be positive");
            log.warning("Number have to be positive");
        }
    }


    private void log(final String message) {
        currencyExchangeViewer.updateLogs(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " " + message);
    }
}
