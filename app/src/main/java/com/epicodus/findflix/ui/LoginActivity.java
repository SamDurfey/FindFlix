package com.epicodus.findflix.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.findflix.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressDialog mAuthProgressDialog;

    @BindView(R.id.emailEditText) EditText mEmailEntry;
    @BindView(R.id.passwordEditText) EditText mPasswordEntry;
    @BindView(R.id.loginButton) Button mLoginButton;
    @BindView(R.id.newAccountLink) TextView mNewAccountLink;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        ButterKnife.bind(this);
        mLoginButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mNewAccountLink.setOnClickListener(this);

        createAuthProgressDialog();
        createAuthStateListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    private void createAuthStateListener() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

    }


    @Override
    public void onClick(View view) {
        if (view == mLoginButton) {
            loginWithPassword();
        }
        if (view == mNewAccountLink) {
            Intent intent = new Intent(LoginActivity.this, NewAccountActivity.class);
            startActivity(intent);
        }
    }

    private void loginWithPassword() {
        String email = mEmailEntry.getText().toString().trim();
        String password = mPasswordEntry.getText().toString().trim();
        if (email.equals("")) {
            mEmailEntry.setError("Please enter your email address");
            return;
        } else if (!isValidEmail(email)) return;

        if (password.equals("")) {
            mPasswordEntry.setError("Please enter your password");
            return;
        }
        mAuthProgressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");

                        } else {
                            Toast.makeText(LoginActivity.this, "Could not sign in. Double-check email and password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailEntry.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }
}
