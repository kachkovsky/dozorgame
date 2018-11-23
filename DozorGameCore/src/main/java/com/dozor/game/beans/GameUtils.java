package com.dozor.game.beans;

/**
 * @author IGOR-K
 */
public class GameUtils {

    public static Player getCurrentPlayer(GameState gameState) {
        return gameState.getPlayers().get(gameState.getTurnPosition().getPlayerIndex());
    }

    public static Player getOtherPlayer(GameState gameState) {
        return gameState.getPlayers().get(getOtherPlayerIndex(gameState.getTurnPosition().getPlayerIndex()));
    }

    public static int getOtherPlayerIndex(int index) {
        return (index == 0) ? 1 : 0;
    }

    public static Unit getCurrentUnit(GameState gameState) {
        return getCurrentPlayer(gameState).getUnitsList().get(gameState.getTurnPosition().getUnitIndex());
    }

    public static boolean hasOtherPlayerUnitWithIdex(GameState gameState, int unitIndex) {
        return unitIndex < 0 || unitIndex > getOtherPlayer(gameState).getUnitsList().size();
    }
}
