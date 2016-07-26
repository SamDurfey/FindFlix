package com.epicodus.nightatthemovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.nightatthemovies.Constants;
import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.models.Show;
import com.epicodus.nightatthemovies.ui.ShowDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    FirebaseUser currentUser;

    public FirebaseShowViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);

    }

    public void bindShow(Show show) {
        ImageView posterImageView = (ImageView) mView.findViewById(R.id.posterImageView);
        TextView titleTextView = (TextView) mView.findViewById(R.id.showTitleTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.with(mContext)
                .load(show.getPosterURL())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(posterImageView);

        titleTextView.setText(show.getShowTitle());
        categoryTextView.setText("Category: " + show.getCategory());
        ratingTextView.setText(show.getRating().concat("/5"));
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Show> shows = new ArrayList<>();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_USER).child(currentUser.getUid()).child(Constants.FIREBASE_CHILD_SHOWS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    shows.add(snapshot.getValue(Show.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, ShowDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("shows", Parcels.wrap(shows));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
