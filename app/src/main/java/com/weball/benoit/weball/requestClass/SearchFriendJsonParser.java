package com.weball.benoit.weball.requestClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benoi on 05/07/2016.
 */
public class SearchFriendJsonParser {
    public SearchFriend parse(JSONObject jsonObject)
    {
        if (jsonObject == null)
            return null;
        String fullName = jsonObject.optString("fullName");
        String photo = jsonObject.optString("photo");
        String id = jsonObject.optString("_id");
        if (fullName != null && photo != null && id != null)
            return new SearchFriend(fullName, photo, id);
        return null;
    }


}


