package com.weball.benoit.weball.row;

import android.content.Context;
import android.util.Log;
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
 * Created by benoi on 09/06/2016.
 */
public class FriendRowAdapter extends ArrayAdapter<Friend_Row>{
    private FriendViewHolder viewHolder;

    public FriendRowAdapter(Context context, List<Friend_Row> rows) {
        super(context, 0, rows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_friend,parent, false);
        }
        FriendViewHolder viewHolder = (FriendViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new FriendViewHolder();
            viewHolder.fullname = (TextView) convertView.findViewById(R.id.name);
            viewHolder.profile_picture = (ImageView) convertView.findViewById(R.id.picture_profile);
            convertView.setTag(viewHolder);
            viewHolder.friend_id = "";
        }
        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Friend_Row friend = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.fullname.setText(friend.getName());
        viewHolder.friend_id = friend.get_id();
        if (!friend.getPhoto().isEmpty())
            Picasso.with(getContext()).load(friend.getPhoto()).placeholder(R.drawable.add_photo_picture).fit().centerCrop().into(viewHolder.profile_picture);

        return convertView;
    }

    public String getFriendId(int position)
    {
        Friend_Row friend = getItem(position);
        Log.d("TEST", friend.get_id());
        Log.d("TEST", friend.get_id());
        Log.d("TEST", friend.get_id());
        Log.d("TEST", friend.get_id());
        Log.d("TEST", friend.get_id());
        Log.d("TEST", friend.get_id());Log.d("TEST", friend.get_id());
        Log.d("TEST", friend.get_id());Log.d("TEST", friend.get_id());



        return friend.get_id();
    }


    private class FriendViewHolder{
        public String   friend_id;
        public TextView fullname;
        public ImageView profile_picture;
    }
}
