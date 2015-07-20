package com.dozor.game.bean.parser;

import com.dozor.game.beans.Unit;
import com.dozor.game.parsers.JsonGameConsts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IGOR-K
 */
public class UnitJsonParser {

    public static JSONArray fromUnitListToJsonArray(List<Unit> units) throws JSONException {
        JSONArray ar = new JSONArray();
        for (Unit unit : units) {
            ar.put(fromUnitToJson(unit));
        }
        return ar;
    }

    public static List<Unit> fromJsonArrayToUnitList(JSONArray ar) throws JSONException {
        List<Unit> units = new ArrayList<>();
        for (int i = 0; i < ar.length(); i++) {
            units.add(fromJsonToUnit(ar.getJSONObject(i)));
        }
        return units;
    }

    public static JSONObject fromUnitToJson(Unit unit) throws JSONException {
        JSONObject o = new JSONObject();
        o.put(JsonGameConsts.HP, unit.getHp());
        o.put(JsonGameConsts.MP, unit.getMp());
        o.put(JsonGameConsts.XP, unit.getXp());
        o.put(JsonGameConsts.STAGE, unit.getStage());
        if (unit.getTribunalPoints() != null) {
            o.put(JsonGameConsts.POINTS, unit.getTribunalPoints().intValue());
        }
        return o;
    }

    public static Unit fromJsonToUnit(JSONObject o) throws JSONException {
        Unit unit = new Unit();
        unit.setHp(o.getInt(JsonGameConsts.HP));
        unit.setXp(o.getInt(JsonGameConsts.XP));
        unit.setMp(o.getInt(JsonGameConsts.MP));
        unit.setStage(o.getInt(JsonGameConsts.STAGE));
        if (o.has(JsonGameConsts.POINTS)) {
            unit.setTribunalPoints(o.getInt(JsonGameConsts.POINTS));
        }
        return unit;
    }
}
