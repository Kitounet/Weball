package com.weball.benoit.weball.row;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weball.benoit.weball.R;

import java.util.List;

/**
 * Created by benoi on 19/02/2016.
 */
public class FiveRowAdapter extends ArrayAdapter<Five_Row>{

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
            viewHolder.five_id = "";
        }
        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Five_Row five = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(five.getName());
        viewHolder.city.setText(five.getCity());
        viewHolder.five_id = five.get_id();
        if (!five.getPhoto().isEmpty())
            Picasso.with(getContext()).load(five.getPhoto()).placeholder(R.drawable.loading).fit().centerCrop().into(viewHolder.background);

        return convertView;
    }

    public String getFiveId(int position)
    {
        Five_Row five = getItem(position);

        return five.get_id();
    }


    private class FiveViewHolder{
        public String   five_id;
        public TextView name;
        public ImageView background;
        public TextView city;
    }
}

