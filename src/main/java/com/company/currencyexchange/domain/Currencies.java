package com.company.currencyexchange.domain;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class Currencies {
    private Map<String, Currency> currencies;

    public void addCurrency(final Currency currency) {
        this.currencies.put(currency.getCode(), currency);
    }

    public Currency getCurrency(final String code) {
        return this.currencies.get(code);
    }
}