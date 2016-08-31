package com.epicodus.findflix.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.findflix.Constants;
import com.epicodus.findflix.R;
import com.epicodus.findflix.models.Show;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.posterImageView) ImageView mPosterImageView;
    @BindView(R.id.showTitleTextView) TextView mShowNameTextView;
    @BindView(R.id.ratingTextView) TextView mRatingTextView;
    @BindView(R.id.summaryTextView) TextView mSummaryTextView;
    @BindView(R.id.nfLinkTextView) TextView mLinkTextView;
    @BindView(R.id.saveShowButton) Button mSaveShowButton;

    private Show mShow;
    FirebaseUser mUser;
    String mSource;

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
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mSource = super.getActivity().getIntent().getStringExtra("source");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mShow.getPosterURL()).into(mPosterImageView);

        mShowNameTextView.setText(mShow.getShowTitle());
        mSummaryTextView.setText(mShow.getSummary());

        mRatingTextView.setText(mShow.getRating().concat("/5"));
        if (mSource.equals("saved")) {
            mSaveShowButton.setText(getResources().getString(R.string.remove_button));
        }

        mLinkTextView.setOnClickListener(this);
        mSaveShowButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == mLinkTextView) {
            Intent linkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mShow.getNfMediaURL()));
            startActivity(linkIntent);
        }
        if (view == mSaveShowButton) {
            DatabaseReference savedShowRef = FirebaseDatabase.getInstance()
                    .getReference(Constants.FIREBASE_CHILD_USER).child(mUser.getUid()).child(Constants.FIREBASE_CHILD_SHOWS);
            savedShowRef.push().setValue(mShow);
            Toast.makeText(getContext(), "Show Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
