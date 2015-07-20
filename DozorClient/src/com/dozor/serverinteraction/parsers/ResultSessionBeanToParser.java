package com.dozor.serverinteraction.parsers;

import com.dozorengine.serverinteraction.bean.SessionResultBean;
import com.dozorengine.serverinteraction.parsers.JsonConsts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IGOR-K
 */
public class ResultSessionBeanToParser {

    public static SessionResultBean resultSessionFromJson(JSONObject o) throws JSONException {
        SessionResultBean srb = new SessionResultBean();
        srb.setSessionPlayerIndex(o.getInt(JsonConsts.CURRENT_PLAYER));
        srb.setSessionNumber(o.getInt(JsonConsts.SESSION_ID));
        JSONArray jsonArray = o.getJSONArray(JsonConsts.NICKS);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        srb.setNicks(list);
        return srb;
    }
}
