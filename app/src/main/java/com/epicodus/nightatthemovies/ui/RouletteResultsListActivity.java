package com.epicodus.nightatthemovies.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.epicodus.nightatthemovies.Constants;
import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.adapters.RouletteResultsListAdapter;
import com.epicodus.nightatthemovies.models.Show;
import com.epicodus.nightatthemovies.services.RouletteService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RouletteResultsListActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    String actorQuery;
    String genreQuery;
    String directorQuery;
    private RouletteResultsListAdapter mResultsListAdapter;

    public ArrayList<Show> mShows;

    @BindView(R.id.listView) RecyclerView mShowListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette_results_list);
        ButterKnife.bind(this);
//        mListView.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        actorQuery = mSharedPreferences.getString(Constants.NFROULETTE_ACTOR_QUERY_PARAMETER, null);
        genreQuery = mSharedPreferences.getString(Constants.NFROULETTE_GENRE_QUERY_PARAMETER, null);
        directorQuery = mSharedPreferences.getString(Constants.NFROULETTE_DIRECTOR_QUERY_PARAMETER,null);
        getFlix();

    }

    private void getFlix() {
        final RouletteService rouletteService = new RouletteService();
        rouletteService.findShows(actorQuery, genreQuery, directorQuery, new  Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    mShows = rouletteService.processResponse(response);
                }

                RouletteResultsListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mResultsListAdapter = new RouletteResultsListAdapter(getApplicationContext(), mShows);
                        mShowListView.setAdapter(mResultsListAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RouletteResultsListActivity.this);
                        mShowListView.setLayoutManager(layoutManager);
                        mShowListView.setHasFixedSize(true);
                    }
                });
            }
        });

    }



    @Override
    public void onClick(View view) {
    }
}
