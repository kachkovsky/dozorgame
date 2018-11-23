package com.dozor.game.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IGOR-K
 */
public class Game {

    private boolean finished = false;
    private List<Player> players = new ArrayList<>();
    private TurnPosition turnPosition;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public TurnPosition getTurnPosition() {
        return turnPosition;
    }

    public void setTurnPosition(TurnPosition turnPosition) {
        this.turnPosition = turnPosition;
    }


}
