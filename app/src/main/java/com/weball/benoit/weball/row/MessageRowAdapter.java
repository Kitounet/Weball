package com.weball.benoit.weball.row;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.weball.benoit.weball.R;

import java.util.List;

/**
 * Created by benoi on 25/03/2016.
 */
public class MessageRowAdapter extends ArrayAdapter<Message_Row> {

    public MessageRowAdapter(Context context, List<Message_Row> rows) {
        super(context, 0, rows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_message,parent, false);
        }
        MessageViewHolder viewHolder = (MessageViewHolder) convertView.getTag();
        if (viewHolder == null)
        {
            viewHolder = new MessageViewHolder();
            viewHolder.user_id = "";
            viewHolder.username = (TextView) convertView.findViewById(R.id.username);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.msg = (TextView) convertView.findViewById(R.id.message);
        }
        Message_Row msg = getItem(position);

        viewHolder.user_id = msg.getUser_id();
        viewHolder.msg.setText(msg.getMessage());
        viewHolder.username.setText(msg.getUsername());
        viewHolder.date.setText(msg.getDate());

        return convertView;
    }

    public String getUserId(int position)
    {
        Message_Row msg = getItem(position);

        return msg.getUser_id();
    }

    private class MessageViewHolder{
        public String   user_id;
        public TextView username;
        public TextView date;
        public TextView msg;
    }
}
