package com.epicodus.nightatthemovies.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.nightatthemovies.Constants;
import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.models.Show;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.posterImageView) ImageView mPosterImageView;
    @BindView(R.id.showNameTextView) TextView mShowNameTextView;
    @BindView(R.id.ratingTextView) TextView mRatingTextView;
    @BindView(R.id.summaryTextView) TextView mSummaryTextView;
    @BindView(R.id.nfLinkTextView) TextView mLinkTextView;

    private Show mShow;

    public static ShowDetailFragment newInstance(Show show) {
        ShowDetailFragment showDetailFragment = new ShowDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("show", Parcels.wrap(show));
        showDetailFragment.setArguments(args);
        return showDetailFragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShow = Parcels.unwrap(getArguments().getParcelable("show"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mShow.getPosterURL()).into(mPosterImageView);

        mShowNameTextView.setText(mShow.getShowTitle());
        mSummaryTextView.setText(mShow.getSummary());

        mLinkTextView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mLinkTextView) {
            Intent linkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mShow.getNfMediaURL()));
            startActivity(linkIntent);
        }
    }
}
