package com.epicodus.nightatthemovies.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.epicodus.nightatthemovies.Constants;
import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.adapters.RouletteResultsListAdapter;
import com.epicodus.nightatthemovies.models.Show;
import com.epicodus.nightatthemovies.services.RouletteService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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
    String genreQuery;
    String directorQuery;
    Response recentResponse;
    private RouletteResultsListAdapter mResultsListAdapter;

    public ArrayList<Show> mShows;

    @BindView(R.id.listView) RecyclerView mShowListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette_results_list);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
//        mListView.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        actorQuery = mSharedPreferences.getString(Constants.NFROULETTE_ACTOR_QUERY_PARAMETER, null);
//        genreQuery = mSharedPreferences.getString(Constants.NFROULETTE_GENRE_QUERY_PARAMETER, null);
        directorQuery = mSharedPreferences.getString(Constants.NFROULETTE_DIRECTOR_QUERY_PARAMETER, null);
        getFlix();

    }

    private void getFlix() {
        final RouletteService rouletteService = new RouletteService();
        rouletteService.findShows(actorQuery, genreQuery, directorQuery, mContext ,new  Callback() {

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
                }




            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_results_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.action_save_list) {
            Log.d("Recent query: ", mSharedPreferences.getString(Constants.RECENT_QUERY, null));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(RouletteResultsListActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
