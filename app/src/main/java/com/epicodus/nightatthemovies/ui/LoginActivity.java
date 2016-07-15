package com.epicodus.nightatthemovies.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.nightatthemovies.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    @Bind(R.id.emailEditText) EditText mEmailEntry;
    @Bind(R.id.passwordEditText) EditText mPasswordEntry;
    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.newAccountLink) TextView mNewAccountLink;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mNewAccountLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mNewAccountLink) {
            Intent intent = new Intent(LoginActivity.this, NewAccountActivity.class);
            startActivity(intent);
        }
    }
}
