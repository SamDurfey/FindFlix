package com.epicodus.nightatthemovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.nightatthemovies.R;
import com.epicodus.nightatthemovies.models.Theater;
import com.epicodus.nightatthemovies.ui.TheaterDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TheaterListAdapter extends RecyclerView.Adapter<TheaterListAdapter.TheaterViewHolder> {
    private ArrayList<Theater> mTheaters = new ArrayList<>();
    private Context mContext;

    public TheaterListAdapter(Context context, ArrayList<Theater> theaters) {
        mContext = context;
        mTheaters = theaters;
    }

    @Override
    public TheaterListAdapter.TheaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theater_list_item, parent, false);
        TheaterViewHolder viewHolder = new TheaterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TheaterListAdapter.TheaterViewHolder holder, int position) {
        holder.bindTheater(mTheaters.get(position));
    }

    @Override
    public int getItemCount() {
        return mTheaters.size();
    }

    public class TheaterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.theaterNameTextView) TextView mNameTextView;
        @BindView(R.id.posterImageView) ImageView mTheaterImageView;

        private Context mContext;

        public TheaterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mContext = itemView.getContext();
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, TheaterDetailActivity.class);
            intent.putExtra("position", Integer.toString(itemPosition));
            intent.putExtra("theaters", Parcels.wrap(mTheaters));
            mContext.startActivity(intent);
        }

        public void bindTheater(Theater theater) {
            Picasso.with(mContext).load(theater.getImageUrl()).into(mTheaterImageView);
            mNameTextView.setText(theater.getName());
        }
    }
}










