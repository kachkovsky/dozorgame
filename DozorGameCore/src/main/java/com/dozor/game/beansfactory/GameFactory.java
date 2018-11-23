package com.dozor.game.beansfactory;

import com.dozor.game.beans.GameState;

/**
 * @author IGOR-K
 */
public class GameFactory {
    public static GameState createGame(int maxPlayers) {
        GameState g = new GameState();
        for (int i = 0; i < maxPlayers; i++) {
            g.getPlayers().add(PlayersFactory.createPlayer(i));
        }
        g.setTurnPosition(TurnPositionFactory.createDefaultTurnPosition());
        g.setFinished(false);
        return g;
    }
}
