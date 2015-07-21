package com.dozor.game.utils;

import com.dozor.game.beans.Unit;

import java.util.List;

/**
 * @author IGOR-K
 */
public class UnitsCalculator {
    public static int getMpForNewUnit(List<Unit> units) {
        return units.size() * 5;
    }
}
