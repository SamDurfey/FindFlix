package com.epicodus.nightatthemovies.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.models.Theater;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TheaterDetailFragment extends Fragment {
    public static final String TAG = TheaterDetailFragment.class.getSimpleName();
    @BindView(R.id.posterImageView) ImageView mTheaterImageView;
    @BindView(R.id.theaterNameTextView) TextView mTheaterNameView;
    @BindView(R.id.ratingTextView) TextView mRatingView;
    @BindView(R.id.websiteTextView) TextView mWebsiteView;
    @BindView(R.id.phoneTextView) TextView mPhoneView;

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

        mTheaterNameView.setText(mTheater.getName());
        // Inflate the layout for this fragment
        return view;
    }

}


























