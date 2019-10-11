package com.ivitesse.epicure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.EpiModel;

import java.util.List;


public class FutureCouponsAdapter extends RecyclerView.Adapter<FutureCouponsAdapter.ViewHolder> {

    private final List<EpiModel> offersList;
    private final Context context;

    public FutureCouponsAdapter(@NonNull List<EpiModel> offersListIn, @NonNull Context ctx) {
        offersList = offersListIn;
        context = ctx;
    }

    @NonNull
    @Override
    public FutureCouponsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                              int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coupons_future, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FutureCouponsAdapter.ViewHolder holder,
                                 int position) {
        EpiModel offersModel = offersList.get(position);
        holder.title.setText(offersModel.getTitle());
        holder.title_desc.setText(offersModel.getDescription());
        holder.offer_valid.setText(offersModel.getOffer_valid());
        holder.offer_from.setText(offersModel.getOffer_from());


    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AppCompatTextView title, title_desc, offer_from, offer_valid;
        final AppCompatButton appCompatButton;

        ViewHolder(View itemview) {
            super(itemview);
            title = itemview.findViewById(R.id.title);
            title_desc = itemview.findViewById(R.id.title_desc);
            offer_from = itemview.findViewById(R.id.offer_from);
            offer_valid = itemview.findViewById(R.id.offer_valid);
            appCompatButton = itemview.findViewById(R.id.appCompatButton);
        }


    }
}