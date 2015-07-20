package com.dozor.game.beansfactory;

import com.dozor.game.beans.Player;

/**
 * @author IGOR-K
 */
public class PlayersFactory {

    public static Player createPlayer(int order) {
        Player p = new Player();
        p.getUnitsList().add(UnitsFactory.createNewUnit(order));
        p.setEvidences(0);
        return p;
    }
}
