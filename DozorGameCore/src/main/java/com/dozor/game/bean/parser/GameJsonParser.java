package com.dozor.game.bean.parser;

import com.dozor.game.beans.Game;
import com.dozor.game.beans.Player;
import com.dozor.game.parsers.JsonGameConsts;
import com.dozorengine.server.bean.INick;
import com.dozorengine.serverinteraction.parsers.JsonConsts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author IGOR-K
 */
public class GameJsonParser {

    public static JSONObject fromGameToJson(Game game, List<? extends INick> users) throws JSONException {
        JSONObject gameObj = new JSONObject();
        gameObj.put(JsonGameConsts.GAME_FINISHED, game.isFinished());
        JSONArray players = new JSONArray();
        for (int i = 0; i < game.getPlayers().size(); i++) {
            JSONObject playerObj = new JSONObject();
            if (users != null) {
                playerObj.put(JsonConsts.NICK, users.get(i).getNick());
            }
            playerObj.put(JsonGameConsts.EVIDENCES, game.getPlayers().get(i).getEvidences());
            playerObj.put(JsonGameConsts.UNITS, UnitJsonParser.fromUnitListToJsonArray(game.getPlayers().get(i).getUnitsList()));
            players.put(playerObj);
        }
        gameObj.put(JsonGameConsts.PLAYERS, players);
        gameObj.put(JsonGameConsts.TURN_POSITION, TurnPositionJsonParser.fromTurnPositionToJson(game.getTurnPosition()));
        return gameObj;
    }

    public static Game fromJsonToGame(JSONObject gameObj) throws JSONException {
        Game g = new Game();
        g.setFinished(gameObj.getBoolean(JsonGameConsts.GAME_FINISHED));
        JSONArray playersArr = gameObj.getJSONArray(JsonGameConsts.PLAYERS);
        for (int i = 0; i < playersArr.length(); i++) {
            JSONObject po = playersArr.getJSONObject(i);
            Player p = new Player();
            p.setEvidences(po.getInt(JsonGameConsts.EVIDENCES));
            p.setUnitsList(UnitJsonParser.fromJsonArrayToUnitList(po.getJSONArray(JsonGameConsts.UNITS)));
            p.setNick(po.optString(JsonConsts.NICK));
            g.getPlayers().add(p);
        }
        g.setTurnPosition(TurnPositionJsonParser.fromJsonToTurnPosition(gameObj.getJSONObject(JsonGameConsts.TURN_POSITION)));
        return g;
    }

}
