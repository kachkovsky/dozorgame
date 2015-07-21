package com.dozor.game.beansfactory;

import com.dozor.game.beans.Game;

/**
 * @author IGOR-K
 */
public class GameFactory {
    public static Game createGame(int maxPlayers) {
        Game g = new Game();
        for (int i = 0; i < maxPlayers; i++) {
            g.getPlayers().add(PlayersFactory.createPlayer(i));
        }
        g.setPosition(TurnPositionFactory.createDefaultTurnPosition());
        g.setFinished(false);
        return g;
    }
}
