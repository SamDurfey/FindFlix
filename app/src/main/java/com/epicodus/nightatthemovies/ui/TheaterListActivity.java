package com.epicodus.nightatthemovies.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.adapters.TheaterListAdapter;
import com.epicodus.nightatthemovies.models.Theater;
import com.epicodus.nightatthemovies.services.GoogleService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class TheaterListActivity extends AppCompatActivity {
    public static final String TAG = TheaterListActivity.class.getSimpleName();
    @Bind(R.id.theatersHeader) TextView mTheatersHeader;
    @Bind(R.id.recyclerView) RecyclerView mTheaterListView;
    private TheaterListAdapter mAdapter;

    public ArrayList<Theater> mTheaters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theaters);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        Typeface limelightFont = Typeface.createFromAsset(getAssets(), "fonts/Limelight.ttf");
        mTheatersHeader.setTypeface(limelightFont);

        String inputtedLocation = intent.getStringExtra("inputtedLocation");
        mTheatersHeader.setText("These are the theaters near " + inputtedLocation + ":");

        getTheaters();
    }

    private void getTheaters() {
        final GoogleService googleService = new GoogleService();
        googleService.findTheaters(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        mTheaters = googleService.processResults(jsonData);
                        for (Theater theater : mTheaters) {
                            Log.d(TAG, theater.getName().toString());
                            Log.d(TAG, "Location: " + theater.getLatLng());
                            Log.d(TAG, theater.getImageUrl());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TheaterListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new TheaterListAdapter(getApplicationContext(), mTheaters);
                        mTheaterListView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TheaterListActivity.this);
                        mTheaterListView.setLayoutManager(layoutManager);
                        mTheaterListView.setHasFixedSize(true);
                    }
                });
            }
        });

    }

}
