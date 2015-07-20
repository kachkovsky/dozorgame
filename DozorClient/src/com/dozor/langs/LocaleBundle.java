package com.dozor.langs;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author IGOR-K
 */
public class LocaleBundle {

    private final static ResourceBundle SELF
            = ResourceBundle.getBundle("com.dozor.langs.locales.Words", new Locale("ru", "RU"));

    public static ResourceBundle getInstance() {
        return SELF;
    }
}
