package com.weball.benoit.weball.custom;

/**
 * Created by benoi on 06/07/2016.
 */
public class Highlight {
    private String attributeName;
    private String highlightedValue;

    public Highlight(String attributeName, String highlightedValue)
    {
        this.attributeName = attributeName;
        this.highlightedValue = highlightedValue;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public String getHighlightedValue()
    {
        return highlightedValue;
    }
}
