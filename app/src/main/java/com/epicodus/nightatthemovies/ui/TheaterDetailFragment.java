package com.epicodus.nightatthemovies.ui;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.models.Theater;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TheaterDetailFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    public static final String TAG = TheaterDetailFragment.class.getSimpleName();
    @Bind(R.id.theaterImageView) ImageView mTheaterImageView;
    @Bind(R.id.theaterNameTextView) TextView mTheaterNameView;
    @Bind(R.id.ratingTextView) TextView mRatingView;
    @Bind(R.id.websiteTextView) TextView mWebsiteView;
    @Bind(R.id.phoneTextView) TextView mPhoneView;
    @Bind(R.id.addressTextView) TextView mAddressView;

    private Theater mTheater;

    public static TheaterDetailFragment newInstance(Theater theater) {
        TheaterDetailFragment theaterDetailFragment = new TheaterDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("theater", Parcels.wrap(theater));
        theaterDetailFragment.setArguments(args);
        return theaterDetailFragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTheater = Parcels.unwrap(getArguments().getParcelable("theater"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theater_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mTheater.getImageUrl()).into(mTheaterImageView);
        mAddressView.setOnClickListener(this);
        mTheaterNameView.setText(mTheater.getName());
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        mContext = getContext();
        if (v == mAddressView) {

            Uri gmIntentUri = Uri.parse("geo:" + mTheater.getLatLng()
                + "?q=(" + mTheater.getName() + ")");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmIntentUri);
            startActivity(mapIntent);
        }
    }
}


























