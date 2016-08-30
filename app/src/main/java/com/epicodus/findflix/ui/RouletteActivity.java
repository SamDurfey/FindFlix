package com.epicodus.findflix.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.findflix.Constants;
import com.epicodus.findflix.R;
import com.epicodus.findflix.models.Show;
import com.epicodus.findflix.services.RouletteService;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;


public class RouletteActivity extends AppCompatActivity implements View.OnClickListener{
    OkHttpClient mClient;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    ArrayList<Show> mShows;

    @BindView(R.id.actorEditText) EditText mActorEntry;
    @BindView(R.id.directorEditText) EditText mDirectorEntry;
    @BindView(R.id.rouletteHeader) TextView mRouletteHeader;
    @BindView(R.id.randomizeButton) Button mRandomizeButton;
    @BindView(R.id.allResultsButton) Button mAllResultsButton;

    public String actorQuery;
    public String directorQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mShows = new ArrayList<>();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        ButterKnife.bind(this);

        Typeface limelightFont = Typeface.createFromAsset(getAssets(), "fonts/Limelight.ttf");
        mRouletteHeader.setTypeface(limelightFont);

        mRandomizeButton.setOnClickListener(this);
        mAllResultsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mRandomizeButton) {
            actorQuery = mActorEntry.getText().toString().trim();
            directorQuery = mDirectorEntry.getText().toString().trim();

            final RouletteService rouletteService = new RouletteService();
            rouletteService.findShows(actorQuery, directorQuery, getApplicationContext(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(RouletteActivity.this, ShowDetailActivity.class);
                        Random random = new Random();
                        mShows = rouletteService.processResponse(response);
                        ArrayList<Show> mRandomlySelectedShow = new ArrayList<>();
                        mRandomlySelectedShow.add(mShows.get(random.nextInt(mShows.size() + 1)));

                        intent.putExtra("shows", Parcels.wrap(mRandomlySelectedShow));

                        startActivity(intent);
                    }
                }
            });

        }
        if (view == mAllResultsButton) {
            actorQuery = mActorEntry.getText().toString().trim();
            directorQuery = mDirectorEntry.getText().toString().trim();

            addToSharedPreferences(Constants.NFROULETTE_ACTOR_QUERY_PARAMETER, actorQuery);
            addToSharedPreferences(Constants.NFROULETTE_DIRECTOR_QUERY_PARAMETER, directorQuery);

            Intent intent = new Intent(RouletteActivity.this, RouletteResultsListActivity.class);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String key, String value) {
        mEditor.putString(key, value).apply();
    }
}
