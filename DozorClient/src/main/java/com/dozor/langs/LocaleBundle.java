package com.dozor.langs;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author IGOR-K
 */
public class LocaleBundle {

    private static class InstanceHolder {
        public static LocaleBundle INSTANCE = new LocaleBundle();
    }

    public static LocaleBundle getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private Properties props = new Properties();

    private LocaleBundle() {
        try {
            InputStream is = getClass().getResourceAsStream("/com/dozor/langs/locales/Words_ru_RU.properties");
            InputStreamReader stream = new InputStreamReader(is, StandardCharsets.UTF_8);
            props.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        return props.getProperty(key);
    }

    public boolean containsKey(String key) {
        return props.containsKey(key);
    }
}
