package com.company.currencyexchange.controller;

import com.company.currencyexchange.controller.exceptions.CurrencyNotFoundException;
import com.company.currencyexchange.controller.exceptions.NonPositiveNumberException;
import com.company.currencyexchange.converterinterface.CurrencyConverter;
import com.company.currencyexchange.domain.Currency;
import com.company.currencyexchange.providers.currency.CurrenciesAccessor;
import com.company.currencyexchange.view.CurrencyExchangeViewer;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import lombok.extern.java.Log;

@Log
public class CurrencyExchangeController implements CurrencyConverter {
    private final CurrenciesAccessor currenciesAccessor;
    private final CurrencyExchangeViewer currencyExchangeViewer;

    public CurrencyExchangeController(final CurrenciesAccessor currenciesAccessor, final CurrencyExchangeViewer currencyExchangeViewer) {
        this.currenciesAccessor = currenciesAccessor;
        this.currencyExchangeViewer = currencyExchangeViewer;
        currencyExchangeViewer.addActionListener(this);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            convertCurrencies(currencyExchangeViewer.getCurrencyIn(), currencyExchangeViewer.getCurrencyOut(), currencyExchangeViewer.getValue());
        } catch (CurrencyNotFoundException currencyNotFoundException) {
            log("Currency not found");
            log.warning("Currency not found");
        } catch (ParserConfigurationException | IOException | SAXException parserConfigurationException) {
            log("Parsing file failed");
            log.warning("Parsing file failed");
        }
    }

    @Override
    public void convertCurrencies(final String in, final String out, final String value) throws ParserConfigurationException, SAXException, IOException {
        Currency currencyIn = currenciesAccessor.getCurrency(in);
        Currency currencyOut = currenciesAccessor.getCurrency(out);

        double parsedValue = validateValue(value);

        double convertedCurrency = parsedValue * (currencyIn.getRate() / currencyIn.getConverter()) / (currencyOut.getRate() / currencyOut.getConverter());
        currencyExchangeViewer.setTextFieldOut(String.valueOf(Math.round(convertedCurrency * 100) / 100.0));
    }

    private double validateValue(final String value) {
        double parsed = 0.0;
        try {
            if (value == null || value.isBlank()) {
                throw new NumberFormatException();
            }
            parsed = Double.parseDouble(value.replace(",", "."));

            if (parsed < 0) {
                parsed = 0.0;
                throw new NonPositiveNumberException("Number have to be positive");
            }
        } catch (NumberFormatException numberFormatException) {
            log("Wrong value entered");
            log.warning("Wrong value entered");
        } catch (NonPositiveNumberException nonPositiveNumberException) {
            log("Number have to be positive");
            log.warning("Number have to be positive");
        }

        return parsed;
    }

    private void log(final String message) {
        currencyExchangeViewer.updateLogs(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " " + message);
    }
}
