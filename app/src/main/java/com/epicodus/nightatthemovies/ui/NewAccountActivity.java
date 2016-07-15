package com.epicodus.nightatthemovies.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.epicodus.nightatthemovies.R;

public class NewAccountActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
    }

    @Override
    public void onClick(View view) {

    }
}
