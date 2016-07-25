package com.epicodus.nightatthemovies.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.nightatthemovies.Constants;
import com.epicodus.nightatthemovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;


public class RouletteActivity extends AppCompatActivity implements View.OnClickListener{
    OkHttpClient mClient;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;

    @BindView(R.id.actorEditText) EditText mActorEntry;
//    @BindView(R.id.genreEditText) EditText mGenreEntry;
    @BindView(R.id.directorEditText) EditText mDirectorEntry;
    @BindView(R.id.rouletteHeader) TextView mRouletteHeader;
    @BindView(R.id.randomizeButton) Button mRandomizeButton;

    public String actorQuery;
    public String genreQuery;
    public String directorQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

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
//            genreQuery = mGenreEntry.getText().toString().trim();
            
            addToSharedPreferences(Constants.NFROULETTE_ACTOR_QUERY_PARAMETER, actorQuery);
            addToSharedPreferences(Constants.NFROULETTE_DIRECTOR_QUERY_PARAMETER, directorQuery);
            // TODO: change genre to category and this all may work.
//            addToSharedPreferences(Constants.NFROULETTE_GENRE_QUERY_PARAMETER, genreQuery);

            Intent intent = new Intent(RouletteActivity.this, RouletteResultsListActivity.class);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String key, String value) {
        mEditor.putString(key, value).apply();
    }
}
