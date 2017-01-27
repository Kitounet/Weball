package com.weball.benoit.weball.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.weball.benoit.weball.requestClass.FieldProfile;
import com.weball.benoit.weball.R;
import com.weball.benoit.weball.requestClass.UserInfo;

/**
 * Created by benoi on 29/03/2016.
 */
public class WeekCalendarFragment extends Fragment{
    ListView mListView;
    private UserInfo mUserInfo;
    private FieldProfile myFive;
    private onWeekListener myListener;

    public WeekCalendarFragment()
    {

    }

    public static WeekCalendarFragment newInstance(UserInfo user, FieldProfile myFive)
    {
        WeekCalendarFragment instance = new WeekCalendarFragment();
        instance.mUserInfo =  user;
        instance.myFive = myFive;
        return instance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View mView = inflater.inflate(R.layout.week_five_calendar, container, false);



        return mView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onWeekListener) {
            myListener = (onWeekListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SelectFragmentListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        myListener = null;
    }

    public interface onWeekListener{

    }

}
