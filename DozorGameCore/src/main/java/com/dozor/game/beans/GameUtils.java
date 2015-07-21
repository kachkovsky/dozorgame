package com.dozor.game.beans;

/**
 * @author IGOR-K
 */
public class GameUtils {

    public static Player getCurrentPlayer(Game game) {
        return game.getPlayers().get(game.getPosition().getPlayerIndex());
    }

    public static Player getOtherPlayer(Game game) {
        return game.getPlayers().get(getOtherPlayerIndex(game.getPosition().getPlayerIndex()));
    }

    public static int getOtherPlayerIndex(int index) {
        return (index == 0) ? 1 : 0;
    }

    public static Unit getCurrentUnit(Game game) {
        return getCurrentPlayer(game).getUnitsList().get(game.getPosition().getUnitIndex());
    }

    public static boolean hasOtherPlayerUnitWithIdex(Game game, int unitIndex) {
        return unitIndex < 0 || unitIndex > getOtherPlayer(game).getUnitsList().size();
    }
}
