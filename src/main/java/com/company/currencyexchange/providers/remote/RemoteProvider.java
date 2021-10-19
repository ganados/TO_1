package com.company.currencyexchange.providers.remote;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import lombok.extern.java.Log;

@Log
public class RemoteProvider {
    public static InputStream getData(final String url) {
        try {
            URL path = new URL(url);
            return path.openStream();
        } catch (IOException e) {
            log.warning("An exception has occurred while opening URL");
        }
        return InputStream.nullInputStream();
    }
}
