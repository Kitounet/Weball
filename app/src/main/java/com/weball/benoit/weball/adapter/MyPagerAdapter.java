package com.weball.benoit.weball.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.astuetz.PagerSlidingTabStrip;
import com.weball.benoit.weball.R;

import java.util.List;

/**
 * Created by benoi on 20/01/2016.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    private List<Fragment> fragments;
    private int tabIcons[] = {R.drawable.notif_logo, R.drawable.group_logo, R.drawable.map_logo, R.drawable.five_logo, R.drawable.user_3x};



    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments, int[] icons)
    {
        super(fm);
        this.fragments = fragments;
        this.tabIcons = icons;
    }

    public void setTabIcons(int[] new_icons)
    {
        this.tabIcons = new_icons;
    }


    @Override
    public Fragment getItem(int position)
    {

        return this.fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return this.fragments.size();
    }

    @Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }

}
