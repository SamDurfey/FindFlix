package com.epicodus.findflix.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.findflix.models.Show;
import com.epicodus.findflix.ui.ShowDetailFragment;

import java.util.ArrayList;

public class ShowPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Show> mShows;

    public ShowPagerAdapter(FragmentManager fm, ArrayList<Show> shows) {
        super(fm);
        mShows = shows;
    }

    @Override
    public Fragment getItem(int position) {
        return ShowDetailFragment.newInstance(mShows.get(position));
    }

    @Override
    public int getCount() {
        return mShows.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mShows.get(position).getShowTitle();
    }
}
