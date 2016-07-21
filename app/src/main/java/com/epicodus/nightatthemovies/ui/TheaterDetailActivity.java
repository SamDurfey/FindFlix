package com.epicodus.nightatthemovies.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.adapters.TheaterPagerAdapter;
import com.epicodus.nightatthemovies.models.Theater;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TheaterDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private TheaterPagerAdapter adapterViewPager;
    ArrayList<Theater> mTheaters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_detail);
        ButterKnife.bind(this);

        mTheaters = Parcels.unwrap(getIntent().getParcelableExtra("theaters"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new TheaterPagerAdapter(getSupportFragmentManager(), mTheaters);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
