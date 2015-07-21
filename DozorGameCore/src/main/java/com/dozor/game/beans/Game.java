package com.dozor.game.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IGOR-K
 */
public class Game {

    private boolean finished = false;
    private List<Player> players = new ArrayList<>();
    private TurnPosition position;

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public TurnPosition getPosition() {
        return position;
    }

    public void setPosition(TurnPosition position) {
        this.position = position;
    }


}
