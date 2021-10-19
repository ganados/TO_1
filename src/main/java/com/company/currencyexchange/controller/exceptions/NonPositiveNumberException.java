package com.company.currencyexchange.controller.exceptions;

public class NonPositiveNumberException extends RuntimeException {
    public NonPositiveNumberException(final String s) {
        super(s);
    }
}
