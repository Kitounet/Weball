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
 * Created by benoi on 14/07/2016.
 */
public class MatchRowAdapter extends ArrayAdapter<Match_Row> {

    public MatchRowAdapter(Context context, List<Match_Row> rows) {
        super(context, 0, rows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_match,parent, false);
        }

        MatchRowHolder viewHolder = (MatchRowHolder) convertView.getTag();

        if(viewHolder == null){
            viewHolder = new MatchRowHolder();
            viewHolder.match_id = "";
            viewHolder.creator_id = "";
            viewHolder.field_id = "";
            viewHolder.status = (TextView) convertView.findViewById(R.id.status_match);
            viewHolder.title_match = (TextView) convertView.findViewById(R.id.title_match);
            viewHolder.profilepicture = (ImageView) convertView.findViewById(R.id.picture_profile);
            viewHolder.creator_name = (TextView) convertView.findViewById(R.id.name_creator);
            viewHolder.time_string = (TextView) convertView.findViewById(R.id.time_match);
            viewHolder.team1_name = (TextView) convertView.findViewById(R.id.team1_name);
            viewHolder.team2_name = (TextView) convertView.findViewById(R.id.team2_name);
            viewHolder.team1_number = (TextView) convertView.findViewById(R.id.number_team1);
            viewHolder.team2_number = (TextView) convertView.findViewById(R.id.number_team2);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Match_Row match = getItem(position);


        //il ne reste plus qu'à remplir notre vue

        viewHolder.match_id = match.getId_match();
        viewHolder.creator_id = match.getId_creator();
        viewHolder.field_id = match.getId_field();
        viewHolder.status.setText(match.getStatus());
        viewHolder.title_match.setText(match.getTitle_match());
        viewHolder.creator_name.setText(match.getCreator_name());
        viewHolder.time_string.setText(match.getTime());
        viewHolder.team1_name.setText(match.getName_team1());
        viewHolder.team2_name.setText(match.getName_team2());
        viewHolder.team1_number.setText(match.getNb_team1());
        viewHolder.team2_number.setText(match.getNb_team2());

        if (!match.getPhoto().isEmpty())
            Picasso.with(getContext()).load(match.getPhoto()).placeholder(R.drawable.add_photo_picture).fit().centerCrop().into(viewHolder.profilepicture);

        return convertView;
    }

    public String getMatchId(int position)
    {
        Match_Row match = getItem(position);

        return match.getId_match();
    }

    public String getCreatorId(int position)
    {
        Match_Row match = getItem(position);

        return match.getId_creator();
    }

    public String getFieldId(int position)
    {
        Match_Row match = getItem(position);

        return match.getId_field();
    }



    private class MatchRowHolder{
        public String   match_id;
        public String   creator_id;
        public String   field_id;
        public TextView status;
        public TextView title_match;
        public TextView creator_name;
        public TextView time_string;
        public ImageView profilepicture;
        public TextView team1_name;
        public TextView team2_name;
        public TextView team1_number;
        public TextView team2_number;
    }


}
