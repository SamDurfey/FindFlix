package com.epicodus.findflix.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.findflix.Constants;
import com.epicodus.findflix.R;
import com.epicodus.findflix.adapters.RouletteResultsListAdapter;
import com.epicodus.findflix.models.Show;
import com.epicodus.findflix.services.RouletteService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RouletteResultsListActivity extends AppCompatActivity {
    private Context mContext;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    String actorQuery;
    String directorQuery;
//    String yearQuery;
//    String titleQuery;

    Response recentResponse;
    private RouletteResultsListAdapter mResultsListAdapter;

    public ArrayList<Show> mShows;

    @BindView(R.id.recyclerView) RecyclerView mShowListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette_results_list);
        ButterKnife.bind(this);
        mContext = getApplicationContext();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        actorQuery = mSharedPreferences.getString(Constants.NFROULETTE_ACTOR_QUERY_PARAMETER, null);
        directorQuery = mSharedPreferences.getString(Constants.NFROULETTE_DIRECTOR_QUERY_PARAMETER, null);
//        yearQuery = mSharedPreferences.getString(Constants.NFROULETTE_YEAR_QUERY_PARAMETER, null);
//        titleQuery = mSharedPreferences.getString(Constants.NFROULETTE_TITLE_QUERY_PARAMETER, null);
        getFlix();
    }

    private void getFlix() {
        final RouletteService rouletteService = new RouletteService();
        rouletteService.findShows(actorQuery, directorQuery, mContext ,new  Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    recentResponse = response;
                    mShows = rouletteService.processResponse(response);

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
                } else {
                    Log.d("Results: ", "oops");
                    Log.d("Results: ", response.body().toString());
                }
            }
        });
    }
}
