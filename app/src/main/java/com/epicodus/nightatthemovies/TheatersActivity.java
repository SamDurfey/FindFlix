package com.epicodus.nightatthemovies;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TheatersActivity extends AppCompatActivity {
    @Bind(R.id.theatersHeader) TextView mTheatersHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theaters);
        ButterKnife.bind(this);

        Typeface limelightFont = Typeface.createFromAsset(getAssets(), "fonts/Limelight.ttf");
        mTheatersHeader.setTypeface(limelightFont);
    }
}
