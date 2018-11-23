package com.dozor.game;

import com.dozor.game.Errors.ErrorsJsonFactory;
import com.dozor.game.bean.parser.FromJsonToActionParser;
import com.dozor.game.bean.parser.GameJsonParser;
import com.dozor.game.beans.GameState;
import com.dozor.game.beans.action.Action;
import com.dozor.game.beansfactory.GameFactory;
import com.dozor.game.parsers.JsonGameConsts;
import com.dozor.serverinteraction.bean.ResponseTypes;
import com.dozorengine.server.Session;
import com.dozorengine.server.gamecontroller.GameDataReceiver;
import com.dozorengine.serverinteraction.StringSocketClient;
import com.dozorengine.serverinteraction.bean.GameResultBean;
import com.dozorengine.serverinteraction.parsers.GameResultBeanParser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class GameControllerAndParser implements GameDataReceiver {

    private final GameTurnCalculator turnCalculator = new GameTurnCalculator();
    private final Session session;
    private volatile GameState gameState;
    private GameResultBean turn;

    public GameControllerAndParser(Session session) {
        this.session = session;
        gameState = GameFactory.createGame(session.getMaxUsers());
        turn = new GameResultBean();
        turn.setTypeOfData(ResponseTypes.TURN.name());
    }

    @Override
    public void parseGameData(StringSocketClient client, String data, int player) {
        try {
            Action action = FromJsonToActionParser.fromJsonToAction(new JSONObject(data));
            turn(true, client, action, player);
        } catch (Throwable e) {
            client.closeAll();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isGameFinished() {
        return gameState.isFinished();
    }

    @Override
    public void calculateAndSendStartData() throws JSONException {
        turn(false, null, null, 0);
    }

    private synchronized void turn(boolean needToCalc, StringSocketClient client, Action action, int player) throws JSONException {
        if (needToCalc) {
            GameState g = turnCalculator.calcTurn(gameState, action, player);
            if (g == null) {
                client.sendString(ErrorsJsonFactory.createErrorIncorrectTurn().toString());
                return;
            } else {
                gameState = g;
            }
        }
        JSONObject jsonObj = new JSONObject();
        JSONObject gameJsonObj = GameJsonParser.fromGameToJson(gameState, session.getUsers());
        jsonObj.put(JsonGameConsts.GAME, gameJsonObj);
        GameResultBeanParser.addGameResultBeanDataToJson(jsonObj, turn);
        session.sendString(jsonObj.toString());
    }
}
