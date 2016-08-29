package com.epicodus.findflix.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.findflix.Constants;
import com.epicodus.findflix.R;
import com.epicodus.findflix.adapters.FirebaseShowViewHolder;
import com.epicodus.findflix.models.Show;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedShowsListActivity extends AppCompatActivity {
    private DatabaseReference mShowReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private FirebaseUser mUser;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_roulette_results_list);
        ButterKnife.bind(this);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mShowReference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_USER)
                .child(mUser.getUid())
                .child(Constants.FIREBASE_CHILD_SHOWS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Show, FirebaseShowViewHolder>
                (Show.class, R.layout.show_list_item, FirebaseShowViewHolder.class, mShowReference) {

            @Override
            protected void populateViewHolder(FirebaseShowViewHolder viewHolder, Show model, int position) {
                viewHolder.bindShow(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
