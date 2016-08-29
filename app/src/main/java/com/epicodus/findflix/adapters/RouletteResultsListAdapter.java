package com.epicodus.findflix.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.findflix.R;
import com.epicodus.findflix.models.Show;
import com.epicodus.findflix.ui.ShowDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RouletteResultsListAdapter extends RecyclerView.Adapter<RouletteResultsListAdapter.ShowViewHolder> {
    private ArrayList<Show> mShows = new ArrayList<>();
    private Context mContext;
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    public RouletteResultsListAdapter(Context context, ArrayList<Show> shows) {
        mContext = context;
        mShows = shows;
    }

    @Override
    public RouletteResultsListAdapter.ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_list_item, parent, false);
        ShowViewHolder viewHolder = new ShowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RouletteResultsListAdapter.ShowViewHolder holder, int position) {
        holder.bindShow(mShows.get(position));
    }

    @Override
    public int getItemCount() {
        return mShows.size();
    }

    public class ShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.showTitleTextView) TextView mNameTextView;
        @BindView(R.id.posterImageView) ImageView mShowImageView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public ShowViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mContext = itemView.getContext();
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, ShowDetailActivity.class );
            intent.putExtra("position", Integer.toString(itemPosition));
            intent.putExtra("shows", Parcels.wrap(mShows));
            mContext.startActivity(intent);
            // TODO: test this intent on your phone. The emulator won't load the show.




            // Comment placeholder for detail fragment:

//            int itemPosition = getLayoutPosition();
//            Intent intent = new Intent(mContext, ShowDetailActivity.class);
//            intent.putExtra("position", Integer.toString(itemPosition));
//            intent.putExtra("shows", Parcels.wrap(mShows));
//            mContext.startActivity(intent);


        }

        public void bindShow(Show show) {
            Picasso.with(mContext).load(show.getPosterURL())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .into(mShowImageView);
            mNameTextView.setText(show.getShowTitle());
            String rating = show.getRating().concat("/5");
            String category = "Category: " + show.getCategory();
            mRatingTextView.setText(rating);
            mCategoryTextView.setText(category);

        }
    }
}










