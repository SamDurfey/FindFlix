package com.epicodus.nightatthemovies.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.nightatthemovies.R;
import com.net.codeusa.NetflixRoulette;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;


public class RouletteActivity extends AppCompatActivity implements View.OnClickListener{
    NetflixRoulette roulette;
    OkHttpClient mClient;

    @Bind(R.id.actorEditText) EditText mActorEntry;
    @Bind(R.id.genreEditText) EditText mGenreEntry;
    @Bind(R.id.directorEditText) EditText mDirectorEntry;
    @Bind(R.id.rouletteHeader) TextView mRouletteHeader;
    @Bind(R.id.randomizeButton) Button mRandomizeButton;

    public String actorQuery;
    public String genreQuery;
    public String directorQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        roulette = new NetflixRoulette();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        ButterKnife.bind(this);

        Typeface limelightFont = Typeface.createFromAsset(getAssets(), "fonts/Limelight.ttf");
        mRouletteHeader.setTypeface(limelightFont);

        mRandomizeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mRandomizeButton) {
            actorQuery = mActorEntry.getText().toString().trim();
            directorQuery = mDirectorEntry.getText().toString().trim();
            genreQuery = mGenreEntry.getText().toString().trim();
        }
        Toast.makeText(RouletteActivity.this, "You will be able to click this button to be given something random to watch.", Toast.LENGTH_LONG).show();
    }
}
