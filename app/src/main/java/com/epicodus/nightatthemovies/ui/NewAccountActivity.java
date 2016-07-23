package com.epicodus.nightatthemovies.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.nightatthemovies.Constants;
import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewAccountActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG =  NewAccountActivity.class.getSimpleName() ;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    DatabaseReference ref;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @BindView(R.id.usernameEditText) EditText mUserNameEntry;
    @BindView(R.id.emailEditText) EditText mEmailEntry;
    @BindView(R.id.passwordEditText) EditText mPasswordEntry;
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEntry;
    @BindView(R.id.signUpButton) Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ref = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_USER);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        ButterKnife.bind(this);

        mSignUpButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mSignUpButton) {
            createNewUser();
        }
    }

    private void createNewUser() {
        final String name = mUserNameEntry.getText().toString().trim();
        final String email = mEmailEntry.getText().toString().trim();
        final String password = mPasswordEntry.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEntry.getText().toString().trim();

        if (!isValidName(name) || !isValidEmail(email) || !isValidPassword(password, confirmPassword)) return;

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = task.getResult().getUser().getUid();
                            User newUser = new User(name, email, password, uid);
                            ref.child(uid).setValue(newUser);
                            Log.d(TAG, "Authentication successful.");
                        } else {
                            Toast.makeText(NewAccountActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
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

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mUserNameEntry.setError("Please enter a user name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPasswordEntry.setError("Your password must contain at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPasswordEntry.setError("These passwords don't match");
            return false;
        }
        return true;
    }

    private void createAuthStateListener() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(NewAccountActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
}
