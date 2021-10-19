package com.company.currencyexchange.providers.currency;

import com.company.currencyexchange.controller.exceptions.CurrencyNotFoundException;
import com.company.currencyexchange.domain.Currencies;
import com.company.currencyexchange.domain.Currency;
import com.company.currencyexchange.providers.remote.RemoteProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Setter
@Getter
public class CurrenciesProvider {
    private static final String LIST_POSITION = "pozycja";
    private static final String CURRENCY_NAME = "nazwa_waluty";
    private static final String CURRENCY_CONVERTER = "przelicznik";
    private static final String CURRENCY_CODE = "kod_waluty";
    private static final String CURRENCY_RATE = "kurs_sredni";

    private static final String CURRENCIES_PATH = "https://www.nbp.pl/kursy/xml/lasta.xml";

    public Currencies createCurrencies() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(RemoteProvider.getData(CURRENCIES_PATH));

        document.getDocumentElement().normalize();

        return getCurrencies(document.getElementsByTagName(LIST_POSITION));
    }

    public Currency getCurrency(final String name) throws CurrencyNotFoundException, IOException, SAXException, ParserConfigurationException {
        for (Currency currency : createCurrencies().getCurrencies().values()) {
            if (currency.getName().equals(name)) {
                return currency;
            }
        }
        log.warning(String.format("Currency %s not found", name));
        throw new CurrencyNotFoundException("There is no such currency");
    }

    private Currencies getCurrencies(final NodeList nodeList) {
        Map<String, Currency> currencies = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Currency currency = getCurrency(element);
                currencies.put(currency.getCode(), currency);
            }
        }
        Currencies providerCurrencies = Currencies.of(currencies);
        providerCurrencies.addCurrency(Currency.of("złoty polski", "PLN", 1, 1.0));

        return providerCurrencies;
    }

    private Currency getCurrency(final Element element) {
        double value = Double.parseDouble(element.getElementsByTagName(CURRENCY_RATE).item(0).getTextContent().replace(",", "."));
        return Currency.builder()
                .name(element.getElementsByTagName(CURRENCY_NAME).item(0).getTextContent())
                .code(element.getElementsByTagName(CURRENCY_CODE).item(0).getTextContent())
                .converter(Integer.parseInt(element.getElementsByTagName(CURRENCY_CONVERTER).item(0).getTextContent()))
                .rate(value)
                .build();
    }
}
