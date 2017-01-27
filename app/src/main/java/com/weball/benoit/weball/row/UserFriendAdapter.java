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
import com.weball.benoit.weball.requestClass.Relationship;
import com.weball.benoit.weball.requestClass.UserFriend;

import java.util.List;

/**
 * Created by benoi on 10/11/2016.
 */

public class UserFriendAdapter extends ArrayAdapter<Relationship> {
    private UserFriendAdapter.FriendViewHolder viewHolder;

    public UserFriendAdapter(Context context, List<Relationship> rows) {
        super(context, 0, rows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_friend,parent, false);
        }
        UserFriendAdapter.FriendViewHolder viewHolder = (UserFriendAdapter.FriendViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new UserFriendAdapter.FriendViewHolder();
            viewHolder.fullname = (TextView) convertView.findViewById(R.id.name);
            viewHolder.profile_picture = (ImageView) convertView.findViewById(R.id.picture_profile);
            convertView.setTag(viewHolder);
            viewHolder.friend_id = "";
        }
        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Relationship friend = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.fullname.setText(friend.getUser().getFullName());
        Log.d("TEST", friend.getId());
        Log.d("TEST", friend.getUser().getId());
        Log.d("TEST", friend.getUser().getId());
        Log.d("TEST", friend.getUser().getId());
        Log.d("TEST", friend.getUser().getId());
        viewHolder.friend_id = friend.getUser().getId();
        if (!friend.getUser().getPhoto().isEmpty())
            Picasso.with(getContext()).load(friend.getUser().getPhoto()).placeholder(R.drawable.user_3x).fit().centerCrop().into(viewHolder.profile_picture);

        return convertView;
    }

    public String getFriendId(int position)
    {
        Relationship friend = getItem(position);

        Log.d("TEST", friend.getUser().getId());
        return friend.getUser().getId();
    }


    private class FriendViewHolder{
        public String   friend_id;
        public TextView fullname;
        public ImageView profile_picture;
    }
}
