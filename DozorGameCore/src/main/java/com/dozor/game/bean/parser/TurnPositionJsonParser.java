package com.dozor.game.bean.parser;

import com.dozor.game.beans.TurnPosition;
import com.dozor.game.parsers.JsonGameConsts;
import com.dozorengine.utils.EnumUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class TurnPositionJsonParser {

    public static JSONObject fromTurnPositionToJson(TurnPosition turnPosition) throws JSONException {
        JSONObject o = new JSONObject();
        o.put(JsonGameConsts.PART_OF_TURN, turnPosition.getPartOfTurn().name());
        o.put(JsonGameConsts.PLAYER_INDEX, turnPosition.getPlayerIndex());
        o.put(JsonGameConsts.UNIT_INDEX, turnPosition.getUnitIndex());
        return o;
    }

    public static TurnPosition fromJsonToTurnPosition(JSONObject o) throws JSONException {
        TurnPosition tp = new TurnPosition();
        tp.setPartOfTurn(EnumUtils.getEnum(TurnPosition.PartOfTurn.class, o.getString(JsonGameConsts.PART_OF_TURN)));
        tp.setPlayerIndex(o.getInt(JsonGameConsts.PLAYER_INDEX));
        tp.setUnitIndex(o.getInt(JsonGameConsts.UNIT_INDEX));
        return tp;
    }
}
