package com.dozor.serverinteraction.parsers;

import com.dozor.game.beans.action.Action;
import com.dozor.game.parsers.JsonGameConsts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class FromActionToJsonParser {

    public static JSONObject fromActionToJson(Action a) throws JSONException {
        JSONObject o = new JSONObject();
        o.put(JsonGameConsts.ACTION, a.getAction().name());
        o.put(JsonGameConsts.UNIT_INDEX, a.getUnitIndex());
        if (a.getPointsList() != null) {
            JSONArray jsonArray = new JSONArray(a.getPointsList());
            o.put(JsonGameConsts.POINTS, jsonArray);
        }
        return o;
    }
}
