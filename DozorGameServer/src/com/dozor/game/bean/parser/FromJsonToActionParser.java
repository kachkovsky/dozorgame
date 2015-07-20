package com.dozor.game.bean.parser;

import com.dozor.game.beans.action.Action;
import com.dozor.game.parsers.JsonGameConsts;
import com.dozorengine.utils.EnumUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IGOR-K
 */
public class FromJsonToActionParser {

    public static Action fromJsonToAction(JSONObject o) throws JSONException {
        Action a = new Action();
        a.setAction(EnumUtils.getEnum(Action.ActionType.class, o.getString(JsonGameConsts.ACTION)));
        if (a.getAction() == null) {
            throw new JSONException("action is null");
        }

        if (o.has(JsonGameConsts.UNIT_INDEX)) {
            a.setUnitIndex(o.getInt(JsonGameConsts.UNIT_INDEX));
        }
        if (o.has(JsonGameConsts.POINTS)) {
            JSONArray jsonArray = o.getJSONArray(JsonGameConsts.POINTS);
            List<Integer> unitsList = new ArrayList<Integer>();
            for (int i = 0; i < jsonArray.length(); i++) {
                unitsList.add((Integer) jsonArray.get(i));
            }
            a.setPointsList(unitsList);
        }
        return a;
    }
}
