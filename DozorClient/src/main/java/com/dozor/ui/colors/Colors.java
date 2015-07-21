package com.dozor.ui.colors;

import java.awt.*;

/**
 * @author IGOR-K
 */
public class Colors {

    public static final Colors INSTANCE = new Colors();

    public static Colors getInstance() {
        return INSTANCE;
    }

    public Color getNormalUnitColor() {
        return Color.LIGHT_GRAY;
    }

    public Color getCurrentUnitColor() {
        return Color.decode("#66FF66");
    }

    public Color getUnitForKillColor() {
        return Color.RED;
    }
}
