package com.dozor.serverinteraction.parsers;

import com.dozorengine.serverinteraction.bean.JoinSessionBean;
import com.dozorengine.serverinteraction.parsers.JsonConsts;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class JoinSessionBeanToParser {

    public static JSONObject toJson(JoinSessionBean bean) throws JSONException {
        JSONObject o = new JSONObject();
        o.put(JsonConsts.LOGIN, bean.getLogin());
        o.put(JsonConsts.NICK, bean.getNick());
        o.put(JsonConsts.SESSION_ID, bean.getSessionNumber());
        return o;
    }
}
