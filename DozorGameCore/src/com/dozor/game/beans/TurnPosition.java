package com.dozor.game.beans;

/**
 * @author IGOR-K
 */
public class TurnPosition {

    public enum PartOfTurn {
        NORMAL,
        BEFORE_TRIBUNAL,
        TRIBUNAL_POINTS,
        TRIBUNAL_KILL;
    }

    private int playerIndex;
    private int unitIndex;
    private PartOfTurn partOfTurn;

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public int getUnitIndex() {
        return unitIndex;
    }

    public void setUnitIndex(int unitIndex) {
        this.unitIndex = unitIndex;
    }

    public PartOfTurn getPartOfTurn() {
        return partOfTurn;
    }

    public void setPartOfTurn(PartOfTurn partOfTurn) {
        this.partOfTurn = partOfTurn;
    }

}
