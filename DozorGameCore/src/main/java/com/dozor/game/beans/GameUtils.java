package com.dozor.game.beans;

/**
 * @author IGOR-K
 */
public class GameUtils {

    public static Player getCurrentPlayer(Game game) {
        return game.getPlayers().get(game.getTurnPosition().getPlayerIndex());
    }

    public static Player getOtherPlayer(Game game) {
        return game.getPlayers().get(getOtherPlayerIndex(game.getTurnPosition().getPlayerIndex()));
    }

    public static int getOtherPlayerIndex(int index) {
        return (index == 0) ? 1 : 0;
    }

    public static Unit getCurrentUnit(Game game) {
        return getCurrentPlayer(game).getUnitsList().get(game.getTurnPosition().getUnitIndex());
    }

    public static boolean hasOtherPlayerUnitWithIdex(Game game, int unitIndex) {
        return unitIndex < 0 || unitIndex > getOtherPlayer(game).getUnitsList().size();
    }
}
