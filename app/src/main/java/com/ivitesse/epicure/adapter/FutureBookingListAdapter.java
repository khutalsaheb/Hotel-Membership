package com.ivitesse.epicure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.EpiModel;

import java.util.List;


public class FutureBookingListAdapter extends RecyclerView.Adapter<FutureBookingListAdapter.ViewHolder> {

    private final List<EpiModel> offersList;
    private final Context context;

    public FutureBookingListAdapter(@NonNull List<EpiModel> offersListIn, @NonNull Context ctx) {
        offersList = offersListIn;
        context = ctx;
    }

    @NonNull
    @Override
    public FutureBookingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                  int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_future_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FutureBookingListAdapter.ViewHolder holder,
                                 int position) {
        EpiModel offersModel = offersList.get(position);
        holder.title.setText(offersModel.getTitle());
        holder.title_desc.setText(offersModel.getTitle_desc());
        holder.dt.setText(offersModel.getDt());
        holder.yeass.setText(offersModel.getYeass());
        holder.original_value.setText(offersModel.getOriginal_value());
        holder.discount_value.setText(offersModel.getDiscount_value());
        holder.saving.setText(offersModel.getSaving());
        holder.title_address.setText(offersModel.getAddress());

    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AppCompatTextView dt, yeass, title, title_desc, original_value, discount_value, saving, title_address;
        final CardView cardview;

        ViewHolder(View itemview) {
            super(itemview);
            dt = itemview.findViewById(R.id.dt);
            yeass = itemview.findViewById(R.id.yeass);
            title = itemview.findViewById(R.id.title);
            title_desc = itemview.findViewById(R.id.title_desc);
            original_value = itemview.findViewById(R.id.original_value);
            discount_value = itemview.findViewById(R.id.discount_value);
            title_address = itemview.findViewById(R.id.title_address);
            cardview = itemview.findViewById(R.id.cardview);
            saving = itemview.findViewById(R.id.saving);
        }

    }
}