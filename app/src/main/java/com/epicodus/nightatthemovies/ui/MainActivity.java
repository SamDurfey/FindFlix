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

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.mainHeader) TextView mMainHeader;
    @Bind(R.id.go2Random) Button mGoToRandomButton;
    @Bind(R.id.go2Theaters) Button mGoToTheatersButton;
    @Bind(R.id.locationEntryView) EditText mLocationEntryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

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
            String inputtedLocation = mLocationEntryView.getText().toString();
            if (inputtedLocation.length() == 0) {
                Toast.makeText(MainActivity.this, "Please enter a location to search for nearby theaters.", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(MainActivity.this, TheaterListActivity.class);
                intent.putExtra("inputtedLocation", inputtedLocation);
                addToSharedPreferences(Constants.PREFERENCES_LOCATION_KEY, inputtedLocation);
                startActivity(intent);
            }
        }
    }

    private void addToSharedPreferences(String key, String value) {
        mEditor.putString(key, value).apply();
    }

}
