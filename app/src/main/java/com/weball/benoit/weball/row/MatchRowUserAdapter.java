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
import com.weball.benoit.weball.requestClass.UserMe;

import java.util.List;

/**
 * Created by benoi on 06/11/2016.
 */

    public class MatchRowUserAdapter extends ArrayAdapter<UserMe.NextMatch> {

        public MatchRowUserAdapter(Context context, List<UserMe.NextMatch> rows) {
            super(context, 0, rows);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_matches_user_profile,parent, false);
            }

            MatchRowHolder viewHolder = (MatchRowHolder) convertView.getTag();

            if(viewHolder == null){
                viewHolder = new MatchRowHolder();
                viewHolder.match_name = (TextView) convertView.findViewById(R.id.state);
                viewHolder.match_id = "";
                viewHolder.profilepicture = (ImageView) convertView.findViewById(R.id.five_picture);
                convertView.setTag(viewHolder);
            }

            //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
            UserMe.NextMatch match = getItem(position);


            //il ne reste plus qu'à remplir notre vue

            viewHolder.match_name.setText(match.getFive().getName());
            viewHolder.match_id = match.getId();

            if (!match.getFive().getPhoto().isEmpty())
                Picasso.with(getContext()).load(match.getFive().getPhoto()).placeholder(R.drawable.add_photo_picture).fit().centerCrop().into(viewHolder.profilepicture);

            return convertView;
        }

        public String getMatchId(int position)
        {
            UserMe.NextMatch match = getItem(position);

            return match.getId();
        }


        private class MatchRowHolder{
            public String   match_id;
            public TextView   match_name;
            public ImageView profilepicture;
        }


    }
