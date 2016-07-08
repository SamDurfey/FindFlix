package com.epicodus.nightatthemovies.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.models.Theater;
import com.epicodus.nightatthemovies.services.GoogleService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class TheatersActivity extends AppCompatActivity {
    public static final String TAG = TheatersActivity.class.getSimpleName();
    @Bind(R.id.theatersHeader) TextView mTheatersHeader;
    @Bind(R.id.listView) ListView mTheaterListView;
    String[] theaterList = new String[] {
            "Living Room Theaters", "Regal Pioneer Place Stadium 6", "Regal Fox Tower Stadium 10",
            "Mission Theater and Pub", "Cinema 21", "Regal Lloyd Center 10 & IMAX",
            "Laurelhurst Theatre & Pub", "Bagdad Theatre", "Kennedy School Theatre"
    };

    public ArrayList<Theater> mTheaters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theaters);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, theaterList);
        mTheaterListView.setAdapter(adapter);

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
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
