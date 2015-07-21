package com.dozor.utils;

import com.dozor.langs.LocaleBundle;

/**
 * @author IGOR-K
 */
public class PlayerColorUtils {

    public static String getColorByIndex(int index) {
        switch (index) {
            case 0:
                return LocaleBundle.getInstance().getString("white");
            case 1:
                return LocaleBundle.getInstance().getString("black");
            case -1:
                return LocaleBundle.getInstance().getString("tribunal");
            default:
                throw new RuntimeException();
        }

    }
}
