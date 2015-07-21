package com.dozor.game.beans;

/**
 * For all responses
 *
 * @author IGOR-K
 */
public enum ResponseType {

    ERROR,
    WAIT_FOR_START,//info about session, you in game
    GAME_SITUATION,
    TRIBUNAL_SITUATION;
}
