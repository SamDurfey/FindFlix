package com.epicodus.nightatthemovies;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RouletteActivity extends AppCompatActivity {
    @Bind(R.id.rouletteHeader) TextView mRouletteHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        ButterKnife.bind(this);

        Typeface limelightFont = Typeface.createFromAsset(getAssets(), "fonts/Limelight.ttf");
        mRouletteHeader.setTypeface(limelightFont);
    }
}
