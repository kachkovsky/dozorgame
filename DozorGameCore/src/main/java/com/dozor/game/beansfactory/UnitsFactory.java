package com.dozor.game.beansfactory;

import com.dozor.game.beans.Unit;

/**
 * @author IGOR-K
 */
public class UnitsFactory {
    public static Unit createNewUnit(int order) {
        Unit unit = new Unit();
        unit.setHp(3);
        unit.setMp(order);
        unit.setStage(0);
        unit.setXp(0);
        return unit;
    }

    public static Unit createNewUnit() {
        return createNewUnit(0);
    }
}
