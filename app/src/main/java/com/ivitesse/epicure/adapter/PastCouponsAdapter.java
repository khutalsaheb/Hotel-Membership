package com.ivitesse.epicure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.EpiModel;

import java.util.List;


public class PastCouponsAdapter extends RecyclerView.Adapter<PastCouponsAdapter.ViewHolder> {

    private final List<EpiModel> offersList;
    private final Context context;

    public PastCouponsAdapter(@NonNull List<EpiModel> offersListIn, @NonNull Context ctx) {
        offersList = offersListIn;
        context = ctx;
    }

    @NonNull
    @Override
    public PastCouponsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_past_coupons, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PastCouponsAdapter.ViewHolder holder,
                                 int position) {
        EpiModel offersModel = offersList.get(position);
        holder.title.setText(offersModel.getTitle());


    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AppCompatTextView title;

        ViewHolder(View itemview) {
            super(itemview);
            title = itemview.findViewById(R.id.title);
        }


    }
}