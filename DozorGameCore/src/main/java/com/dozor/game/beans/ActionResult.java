package com.dozor.game.beans;

public class ActionResult {
    private GameState gameState;
    private String errorCode;

    public ActionResult(GameState gameState) {
        this.gameState = gameState;
    }

    public ActionResult(String errorCode) {
        this.errorCode = errorCode;
    }

    public GameState getGameState() {
        return gameState;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static ActionResult create(Enum errorCode) {
        return new ActionResult(errorCode.name());
    }
}
