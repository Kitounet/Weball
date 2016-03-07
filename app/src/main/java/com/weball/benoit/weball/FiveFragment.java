package com.weball.benoit.weball;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.List;
import java.util.Vector;

/**
 * Created by benoi on 16/02/2016.
 */
public class FiveFragment extends Fragment{
    private UserInfo  mUserInfo;

    public FiveFragment() {
    }

    public static FiveFragment newInstance(UserInfo mUserInfo)
    {
        FiveFragment instance = new FiveFragment();
        instance.mUserInfo = mUserInfo;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.network_five, container, false);
        String[] titles = {"MAP", "LISTE"};


        List<Fragment> fragments = new Vector<Fragment>();
        Log.d("TEST", mUserInfo.getToken());

        fragments.add(MatchFiveFragment.newInstance(mUserInfo));
        fragments.add(ListFiveFragment.newInstance(mUserInfo));

        ViewPager pager = (ViewPager) mView.findViewById(R.id.viewpager);
        pager.setAdapter(new MyPagerAdapter(getChildFragmentManager(), fragments, titles));
        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) mView.findViewById(R.id.tabs);
        tabs.setTextColor(getResources().getColor(R.color.white));
        tabs.setIndicatorColor(getResources().getColor(R.color.green));
        tabs.setUnderlineColor(getResources().getColor(R.color.green));
        tabs.setViewPager(pager);

        return mView;
    }

}
