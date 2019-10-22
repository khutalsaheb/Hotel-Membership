package com.ivitesse.epicure.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.Models_Hotel_Booking;

import java.util.ArrayList;
import java.util.List;

public class Hotel_Home_Adapter extends RecyclerView.Adapter<Hotel_Home_Adapter.NewsViewHolder> {
    private final List<Models_Hotel_Booking> mNews;
    private final DetailsListener listener;
    //  private ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

    public Hotel_Home_Adapter(@Nullable ArrayList<Models_Hotel_Booking> news, @Nullable DetailsListener listener) {
        mNews = news;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_hotel_list, viewGroup, false);

        return new NewsViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final Models_Hotel_Booking news = mNews.get(position);
        holder.hotelname.setText(news.getPlacename());
        holder.txt_hotelreview.setText(news.getReview() + "  Reviews");
        holder.txt_offer_deal.setText(news.getOffer());
        holder.txt_offer_deal.setSelected(true);
        holder.txt_placeprice.setText(news.getPlaceprices());
        holder.location_image.setImageResource(news.getDimage());
        holder.rating.setRating(Float.parseFloat(news.getStar()));
        holder.rating.setNumStars(5);
        holder.rating.setStepSize((float) 0.1);
        holder.card_action.setOnClickListener(v -> listener.DealsDetailsListener(news));
    }

    @Override
    public int getItemCount() {
        return mNews.size();

    }

    public interface DetailsListener {
        void DealsDetailsListener(@Nullable Models_Hotel_Booking details);

    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        final AppCompatRatingBar rating;
        final AppCompatTextView hotelname, txt_hotelreview, txt_offer_deal, txt_placeprice;
        final AppCompatImageView location_image;
        final CardView card_action;
        final AppCompatImageButton likebutton;

        NewsViewHolder(View itemView) {
            super(itemView);
            location_image = itemView.findViewById(R.id.location_image);
            likebutton = itemView.findViewById(R.id.likebutton);
            hotelname = itemView.findViewById(R.id.hotelname);
            txt_hotelreview = itemView.findViewById(R.id.txt_hotelreview);
            txt_offer_deal = itemView.findViewById(R.id.txt_offer_deal);
            card_action = itemView.findViewById(R.id.card_action);
            txt_placeprice = itemView.findViewById(R.id.txt_placeprice);
            rating = itemView.findViewById(R.id.rating);
        }

    }

}