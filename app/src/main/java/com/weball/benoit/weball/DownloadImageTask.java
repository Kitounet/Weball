package com.weball.benoit.weball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by benoi on 25/02/2016.
 */
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    RelativeLayout  bmImage;
    Context         context;
    public DownloadImageTask(RelativeLayout bmImage, Context mContext) {
        this.bmImage = bmImage;
        this.context = mContext;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if (result != null)
        {
            Drawable d = new BitmapDrawable(this.context.getResources(), result);
            bmImage.setBackgroundDrawable(d);
        }
    }
}