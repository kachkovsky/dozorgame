package com.dozor.serverinteraction.parsers;

import com.dozor.serverinteraction.bean.Errors;
import com.dozor.serverinteraction.bean.ResponseTypes;
import com.dozorengine.serverinteraction.parsers.JsonConsts;
import com.dozorengine.utils.EnumUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author IGOR-K
 */
public class JsonToGameResultBeanParser {

    public static ResponseTypes getResponseType(JSONObject o) throws JSONException {
        if (o.has(JsonConsts.RESPONSE_TYPE)) {
            return EnumUtils.getEnum(ResponseTypes.class, o.getString(JsonConsts.RESPONSE_TYPE));
        }
        return null;
    }

    public static Errors getErrors(JSONObject o) throws JSONException {
        if (o.has(JsonConsts.ERROR)) {
            return EnumUtils.getEnum(Errors.class, o.getString(JsonConsts.ERROR));
        }
        return null;
    }
}
