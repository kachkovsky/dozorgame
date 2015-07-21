package com.dozor.serverinteraction.parsers;

import com.dozorengine.serverinteraction.bean.CreateSessionBean;
import com.dozorengine.serverinteraction.parsers.JsonConsts;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class CreateSessionBeanToParser {

    public static JSONObject toJson(CreateSessionBean bean) throws JSONException {
        JSONObject o = new JSONObject();
        o.put(JsonConsts.LOGIN, bean.getLogin());
        o.put(JsonConsts.NICK, bean.getNick());
        o.put(JsonConsts.PLAYERS_COUNT, bean.getMaxPlayers());
        return o;
    }
}
