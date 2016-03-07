package com.weball.benoit.weball;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by benoi on 20/01/2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments;
    private String[] TITLES = {"ACCUEIL", "INBOX", "FIVE", "MATCHS", "PROFIL"};



    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles)
    {
        super(fm);
        this.fragments = fragments;
        TITLES = titles;
    }

    public void SetTitles(String[] new_titles)
    {
        TITLES = new_titles;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return TITLES[position];
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

}
