package com.weball.benoit.weball.custom;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;

import com.weball.benoit.weball.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by benoi on 06/07/2016.
 */
public class HighlightRenderer {
    private Context context;

    // NOTE: This pattern is not bullet-proof (most notably against nested tags), but it is
    // sufficient for our purposes.
    static final Pattern HIGHLIGHT_PATTERN = Pattern.compile("<em>([^<]*)</em>");

    public HighlightRenderer(Context context)
    {
        this.context = context;
    }

    public Spannable renderHighlights(String markupString)
    {
        SpannableStringBuilder result = new SpannableStringBuilder();
        Matcher matcher = HIGHLIGHT_PATTERN.matcher(markupString);
        int p = 0; // current position in input string
        int q = 0; // current position in output string
        // For each highlight...
        while (matcher.find()) {
            // Append text before.
            result.append(markupString.substring(p, matcher.start()));
            q += matcher.start() - p;
            p = matcher.start();

            // Append highlighted text.
            String highlightString = matcher.group(1);
            result.append(highlightString);
            result.setSpan(new BackgroundColorSpan(context.getResources().getColor(R.color.colorAccent)), q, q + highlightString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            q += highlightString.length();
            p = matcher.end();
        }
        // Append text after.
        result.append(markupString.substring(p));
        return result;
    }
}
