package com.epicodus.findflix.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.findflix.models.Theater;
import com.epicodus.findflix.ui.TheaterDetailFragment;

import java.util.ArrayList;

public class TheaterPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Theater> mTheaters;

    public TheaterPagerAdapter(FragmentManager fm, ArrayList<Theater> theaters) {
        super(fm);
        mTheaters = theaters;
    }

    @Override
    public Fragment getItem(int position) {
        return TheaterDetailFragment.newInstance(mTheaters.get(position));
    }

    @Override
    public int getCount() {
        return mTheaters.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTheaters.get(position).getName();
    }
}
