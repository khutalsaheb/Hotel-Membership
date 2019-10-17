package com.ivitesse.epicure.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.Models_Hotel_Booking;

import java.util.ArrayList;
import java.util.List;

public class Deals_Adapter extends RecyclerView.Adapter<Deals_Adapter.NewsViewHolder> {
    private final List<Models_Hotel_Booking> mNews;
    private final DetailsListener listener;
    //  private ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

    public Deals_Adapter(@NonNull ArrayList<Models_Hotel_Booking> news, @NonNull DetailsListener listener) {
        mNews = news;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_activity_deals, viewGroup, false);

        return new NewsViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final Models_Hotel_Booking news = mNews.get(position);
        holder.txt_placename.setText(news.getPlacename());
        holder.txt_placelocation.setText(news.getPlacelocation());
        holder.txt_days.setText(news.getDays());
        holder.txt_placedeals.setText(news.getDaysleft());
        holder.txt_placeprice.setText(news.getPlaceprices());
        holder.location_image.setImageResource(news.getDimage());
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

        final AppCompatTextView txt_placename, txt_placelocation, txt_days, txt_placedeals, txt_placeprice;
        final AppCompatImageView location_image;
        final CardView card_action;

        NewsViewHolder(View itemView) {
            super(itemView);
            location_image = itemView.findViewById(R.id.location_image);
            txt_placedeals = itemView.findViewById(R.id.txt_placedeals);
            txt_placename = itemView.findViewById(R.id.txt_placename);
            txt_placelocation = itemView.findViewById(R.id.txt_placelocation);
            txt_days = itemView.findViewById(R.id.txt_days);
            card_action = itemView.findViewById(R.id.card_action);
            txt_placeprice = itemView.findViewById(R.id.txt_placeprice);
        }

    }

}