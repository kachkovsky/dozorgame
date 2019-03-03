package com.dozor.langs;

import com.joconner.i18n.Utf8ResourceBundleControl;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author IGOR-K
 */
public class LocaleBundle {
    private final static ResourceBundle.Control UTF_8_CONTROL = new Utf8ResourceBundleControl();
    private final static ResourceBundle SELF;

    static {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("com.dozor.langs.locales.Words", new Locale("ru", "RU"));
        } catch (MissingResourceException e) {
            e.printStackTrace();
            bundle = ResourceBundle.getBundle("com.dozor.langs.locales.Words", new Locale("ru", "RU"), UTF_8_CONTROL);
        }
        SELF = bundle;
    }


    public static ResourceBundle getInstance() {
        return SELF;
    }
}
