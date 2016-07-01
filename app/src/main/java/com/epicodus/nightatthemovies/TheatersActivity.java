package com.epicodus.nightatthemovies;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TheatersActivity extends AppCompatActivity {
    @Bind(R.id.theatersHeader) TextView mTheatersHeader;
    @Bind(R.id.listView) ListView mTheaterListView;
    String[] theaterList = new String[] {
            "Living Room Theaters", "Regal Pioneer Place Stadium 6", "Regal Fox Tower Stadium 10",
            "Mission Theater and Pub", "Cinema 21", "Regal Lloyd Center 10 & IMAX",
            "Laurelhurst Theatre & Pub", "Bagdad Theatre", "Kennedy School Theatre"

    };


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
    }
}
