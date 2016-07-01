package com.epicodus.nightatthemovies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mainHeader) TextView mMainHeader;
    @Bind(R.id.go2Random) Button mGoToRandomButton;
    @Bind(R.id.go2Theaters) Button mGoToTheatersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface limelightFont = Typeface.createFromAsset(getAssets(), "fonts/Limelight.ttf");
        mMainHeader.setTypeface(limelightFont);

        mGoToRandomButton.setOnClickListener(this);
        mGoToTheatersButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == mGoToRandomButton) {
            Intent intent = new Intent(MainActivity.this, RouletteActivity.class);
            startActivity(intent);
        }
        if (view == mGoToTheatersButton) {
            Intent intent = new Intent(MainActivity.this, TheatersActivity.class);
            startActivity(intent);
        }
    }
}
