package com.epicodus.nightatthemovies.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.nightatthemovies.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RouletteActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.rouletteHeader) TextView mRouletteHeader;
    @Bind(R.id.randomizeButton) Button mRandomizeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        ButterKnife.bind(this);

        Typeface limelightFont = Typeface.createFromAsset(getAssets(), "fonts/Limelight.ttf");
        mRouletteHeader.setTypeface(limelightFont);

        mRandomizeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(RouletteActivity.this, "You will be able to click this button to be given something random to watch.", Toast.LENGTH_LONG).show();
    }
}
