package com.company.currencyexchange.controller.exceptions;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(final String s) {
        super(s);
    }
}
