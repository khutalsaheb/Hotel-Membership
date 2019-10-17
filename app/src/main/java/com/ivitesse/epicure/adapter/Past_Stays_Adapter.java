package com.ivitesse.epicure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.EpiModel;

import java.util.List;


public class Past_Stays_Adapter extends RecyclerView.Adapter<Past_Stays_Adapter.ViewHolder> {

    private final List<EpiModel> offersList;
    private final Context context;

    public Past_Stays_Adapter(@NonNull List<EpiModel> offersListIn, @NonNull Context ctx) {
        offersList = offersListIn;
        context = ctx;
    }

    @NonNull
    @Override
    public Past_Stays_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_past_stays_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Past_Stays_Adapter.ViewHolder holder,
                                 int position) {
        EpiModel offersModel = offersList.get(position);
        holder.title.setText(offersModel.getTitle());
        holder.title_address.setText(offersModel.getAddress());

    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AppCompatTextView title, date_text, title_address;
        final AppCompatImageView hotel_image;

        ViewHolder(View itemview) {
            super(itemview);
            hotel_image = itemview.findViewById(R.id.hotel_image);
            title = itemview.findViewById(R.id.title);
            date_text = itemview.findViewById(R.id.date_text);
            title_address = itemview.findViewById(R.id.title_address);
        }

    }
}