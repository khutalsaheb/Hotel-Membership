package com.ivitesse.epicure.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.Models_Hotel_Booking;

import java.util.List;

public class Discover_Adapter extends RecyclerView.Adapter<Discover_Adapter.NewsViewHolder> {
    private final List<Models_Hotel_Booking> mNews;
    private final DiscoverDetailsListener listener;
    private Context context;
    // private ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

    public Discover_Adapter(@Nullable Context context, @Nullable List<Models_Hotel_Booking> news, @Nullable DiscoverDetailsListener listener) {
        mNews = news;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_discover, viewGroup, false);

        return new NewsViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final Models_Hotel_Booking news = mNews.get(position);
        holder.txt_placename.setText(news.getPlacename());
        holder.txt_placelocation.setText(news.getPlacelocation());
        holder.txt_placelikes.setText(news.getLikes());
        holder.txt_placeprice.setText(news.getPlaceprices());
        holder.location_image.setImageResource(news.getDimage());
        holder.rating.setRating(Float.parseFloat(news.getStar()));
        holder.rating.setNumStars(5);
        holder.rating.setStepSize((float) 0.1);
        //  holder.location_image.setImageUrl(news.getPlaceimage(), imageLoader);
        // thumbnail image
        //  holder.location_image.setImageUrl(m.getThumbnailUrl(), imageLoader);

        holder.card_action.setOnClickListener(v -> listener.DiscoverDealsDetailsListener(news));
    }

    @Override
    public int getItemCount() {
        return mNews.size();

    }

    public interface DiscoverDetailsListener {
        void DiscoverDealsDetailsListener(@Nullable Models_Hotel_Booking details);

    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        final private AppCompatRatingBar rating;

        final private AppCompatTextView txt_placename, txt_placelocation, txt_placelikes, txt_placeprice;
        final private AppCompatImageView location_image;
        final private CardView card_action;

        NewsViewHolder(View itemView) {
            super(itemView);
            location_image = itemView.findViewById(R.id.location_image);
            txt_placelikes = itemView.findViewById(R.id.txt_placelikes);
            txt_placename = itemView.findViewById(R.id.txt_placename);
            txt_placelocation = itemView.findViewById(R.id.txt_placelocation);
            card_action = itemView.findViewById(R.id.card_action);
            txt_placeprice = itemView.findViewById(R.id.txt_placeprice);
            rating = itemView.findViewById(R.id.rating);

        }

    }

}