package com.ivitesse.epicure.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.helper.CircularImageView;
import com.ivitesse.epicure.model.Models_Hotel_Booking;

import java.util.ArrayList;
import java.util.List;


public class Amenities_Adapter extends RecyclerView.Adapter<Amenities_Adapter.NewsViewHolder> {
    private final List<Models_Hotel_Booking> mNews;
    private Context context;

    public Amenities_Adapter(@Nullable Context context, @Nullable ArrayList<Models_Hotel_Booking> news) {
        mNews = news;
        this.context = context;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_amenities, viewGroup, false);

        return new NewsViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final Models_Hotel_Booking news = mNews.get(position);
        holder.txt_servicename.setText(news.getPlacename());
        holder.circle_service_ImageView.setImageResource(news.getDimage());
    }

    @Override
    public int getItemCount() {
        return mNews.size();

    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        final AppCompatTextView txt_servicename;
        final CircularImageView circle_service_ImageView;

        NewsViewHolder(View itemView) {
            super(itemView);
            circle_service_ImageView = itemView.findViewById(R.id.amenitiesImage);
            txt_servicename = itemView.findViewById(R.id.amenitiesname);

        }

    }

}