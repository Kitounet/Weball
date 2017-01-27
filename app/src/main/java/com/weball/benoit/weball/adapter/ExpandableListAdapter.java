package com.weball.benoit.weball.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.fragment.MatchProfileFragment;
import com.weball.benoit.weball.requestClass.FieldProfile;
import com.weball.benoit.weball.requestClass.MatchInfo;
import com.weball.benoit.weball.requestClass.Matchs;
import com.weball.benoit.weball.requestClass.UserInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by benoi on 28/03/2016.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter{
    private Context mContext;
    private UserInfo                        muser;
    private List<MatchInfo.Team>    mListDataHeader;
    private HashMap<MatchInfo.Team, List<MatchProfileFragment.User>> mlistDataChild;


    public ExpandableListAdapter(Context context, List<MatchInfo.Team> dataheaders, HashMap<MatchInfo.Team, List<MatchProfileFragment.User>> userList, UserInfo user) {
        this.mContext = context;
        this.mlistDataChild = userList;
        this.mListDataHeader = dataheaders;
        this.muser = user;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.mlistDataChild.get(this.mListDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final MatchProfileFragment.User user = (MatchProfileFragment.User) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtname = (TextView) convertView
                .findViewById(R.id.name_player);
        ImageView picture = (ImageView) convertView.findViewById(R.id.circle);
        TextView    inscription = (TextView) convertView.findViewById(R.id.team_name);

        if (!user.get_id().equals("addrow"))
        {
            txtname.setVisibility(View.VISIBLE);
            picture.setVisibility(View.VISIBLE);
            txtname.setText(user.getFullName());
            if (muser.get_id().equals(user.get_id()))
            {
                inscription.setEnabled(true);
                inscription.setText("QUITTER");
                inscription.setTextColor(this.mContext.getResources().getColor(R.color.red));
            }
            else {
                inscription.setEnabled(false);
            }

            if (!user.getPhoto().isEmpty())
                Picasso.with(this.mContext).load(user.getPhoto()).placeholder(R.drawable.add_photo_picture).fit().centerCrop().into(picture);
        }
        else
        {
            txtname.setVisibility(View.GONE);
            picture.setVisibility(View.GONE);
            inscription.setEnabled(true);
            inscription.setText("S'INSCRIRE");
            inscription.setTextColor(this.mContext.getResources().getColor(R.color.green));
        }

        return convertView;
    }


    @Override
    public int  getChildrenCount(int groupPosition)
    {
        return this.mlistDataChild.get(this.mListDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object   getGroup(int groupPosition)
    {
        return this.mListDataHeader.get(groupPosition);
    }


    @Override
    public int getGroupCount()
    {
        return this.mListDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        MatchInfo.Team headerTitle = (MatchInfo.Team) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView nb_player = (TextView) convertView
                .findViewById(R.id.nb_player);
        TextView team_name = (TextView) convertView.findViewById(R.id.team_name);

        nb_player.setText(String.valueOf(headerTitle.getCurrentPlayers()));
        team_name.setText(headerTitle.getName());

        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

}
