package com.dozor.game.beansfactory;

import com.dozor.game.beans.TurnPosition;
import com.dozor.game.beans.TurnPosition.PartOfTurn;

/**
 * @author IGOR-K
 */
public class TurnPositionFactory {
    public static TurnPosition createDefaultTurnPosition() {
        TurnPosition tp = new TurnPosition();
        tp.setPlayerIndex(0);
        tp.setUnitIndex(0);
        tp.setPartOfTurn(PartOfTurn.NORMAL);
        return tp;
    }
}
