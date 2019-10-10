package com.ivitesse.epicure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.EpiModel;

import java.util.List;


public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {

    private static int currentPosition = 0;
    private final List<EpiModel> offersList;
    private final Context context;

    public AboutAdapter(@NonNull List<EpiModel> offersListIn, @NonNull Context ctx) {
        offersList = offersListIn;
        context = ctx;
    }

    @NonNull
    @Override
    public AboutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutAdapter.ViewHolder holder,
                                 int position) {
        EpiModel offersModel = offersList.get(position);
        holder.cab_name.setText(offersModel.getName());
        holder.base_value.setText(offersModel.getDescription());

        holder.layout.setVisibility(View.GONE);
        if (currentPosition == position) {
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            holder.layout.setVisibility(View.VISIBLE);
            holder.layout.startAnimation(slideDown);
        }

        holder.ll.setOnClickListener(view -> {
            currentPosition = position;

            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AppCompatTextView cab_name, base_value;
        final LinearLayoutCompat ll, layout;

        ViewHolder(View itemview) {
            super(itemview);
            cab_name = itemview.findViewById(R.id.cab_name);
            layout = itemview.findViewById(R.id.linearLayout);
            base_value = itemview.findViewById(R.id.base);
            ll = itemview.findViewById(R.id.ll);


        }


    }
}