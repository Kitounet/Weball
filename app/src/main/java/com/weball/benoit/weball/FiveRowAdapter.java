package com.weball.benoit.weball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by benoi on 19/02/2016.
 */
public class FiveRowAdapter extends ArrayAdapter<Five_Row>{
    private FiveViewHolder viewHolder;

    public FiveRowAdapter(Context context, List<Five_Row> rows) {
        super(context, 0, rows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


    if(convertView == null){
         convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_five,parent, false);
    }
    FiveViewHolder viewHolder = (FiveViewHolder) convertView.getTag();
    if(viewHolder == null){
        viewHolder = new FiveViewHolder();
        viewHolder.name = (TextView) convertView.findViewById(R.id.five_name);
        viewHolder.city = (TextView) convertView.findViewById(R.id.pos);
        viewHolder.background = (ImageView) convertView.findViewById(R.id.backgroundImage);
        convertView.setTag(viewHolder);
    }
        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
    Five_Row five = getItem(position);

    //il ne reste plus qu'à remplir notre vue
    viewHolder.name.setText(five.getName());
    viewHolder.city.setText(five.getCity());
    if (!five.getPhoto().getUrl().isEmpty())
        Picasso.with(getContext()).load(five.getPhoto().getUrl()).fit().centerCrop().into(viewHolder.background);

    return convertView;
    }


    private class FiveViewHolder{
        public TextView name;
        public ImageView background;
        public TextView city;
    }
}

