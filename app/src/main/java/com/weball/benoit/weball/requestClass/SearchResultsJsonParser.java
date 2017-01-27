package com.weball.benoit.weball.requestClass;

import com.weball.benoit.weball.custom.Highlight;
import com.weball.benoit.weball.custom.HighlightedResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benoi on 06/07/2016.
 */
public class SearchResultsJsonParser
{
    private SearchFriendJsonParser  searchFriendParser = new SearchFriendJsonParser();

    public List<HighlightedResult<SearchFriend>> parseResults(JSONObject jsonObject)
    {
        if (jsonObject == null)
            return null;

        List<HighlightedResult<SearchFriend>> results = new ArrayList<>();
        JSONArray hits = jsonObject.optJSONArray("hits");
        if (hits == null)
            return null;

        for (int i = 0; i < hits.length(); ++i) {
            JSONObject hit = hits.optJSONObject(i);
            if (hit == null)
                continue;

            SearchFriend searchFriend = searchFriendParser.parse(hit);
            if (searchFriend == null)
                continue;

            JSONObject highlightResult = hit.optJSONObject("_highlightResult");
            if (highlightResult == null)
                continue;
            JSONObject highlightTitle = highlightResult.optJSONObject("fullName");
            if (highlightTitle == null)
                continue;
            String value = highlightTitle.optString("value");
            if (value == null)
                continue;
            HighlightedResult<SearchFriend> result = new HighlightedResult<>(searchFriend);
            result.addHighlight("fullName", new Highlight("fullName", value));
            results.add(result);
        }
        return results;
    }
}
