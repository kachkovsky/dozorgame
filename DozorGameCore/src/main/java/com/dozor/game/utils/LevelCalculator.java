package com.dozor.game.utils;

/**
 * @author IGOR-K
 */
public class LevelCalculator {

    public static int getLevelByExp(int exp) {
        return ((int) (-1 + Math.sqrt(1 + 8 * exp)) / 2) + 1;
    }

}
