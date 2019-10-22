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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.EpiModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FutureCouponsAdapter extends RecyclerView.Adapter<FutureCouponsAdapter.ViewHolder> {

    private final List<EpiModel> offersList;
    private final Context context;
    private DetailsListener detailsListeners;

    public FutureCouponsAdapter(@NonNull List<EpiModel> offersListIn, @NonNull Context ctx, @NonNull DetailsListener detailsListener) {
        offersList = offersListIn;
        context = ctx;
        detailsListeners = detailsListener;
    }

    @NonNull
    @Override
    public FutureCouponsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                              int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coupons_future, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FutureCouponsAdapter.ViewHolder holder,
                                 int position) {
        EpiModel offersModel = offersList.get(position);
        holder.title.setText(offersModel.getTitle());
        holder.title_desc.setText(offersModel.getDescription());
        holder.offer_valid.setText("Offer Expires " + offersModel.getOffer_valid());
        holder.offer_from.setText(offersModel.getOffer_from());
        holder.cardview.setOnClickListener(v -> detailsListeners.DealsDetailsListener(offersModel));
        holder.appCompatButton.setOnClickListener(v -> detailsListeners.DealsDetailsListener(offersModel));
        Picasso.get()
                .load(offersModel.getProfile_pic())
                .fit().centerCrop()
                .into(holder.networkImageView);

    }

    public interface DetailsListener {
        void DealsDetailsListener(@Nullable EpiModel details);

    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AppCompatTextView title, title_desc, offer_from, offer_valid;
        final MaterialButton appCompatButton;
        final AppCompatImageView networkImageView;
        MaterialCardView cardview;

        ViewHolder(View itemview) {
            super(itemview);
            title = itemview.findViewById(R.id.title);
            title_desc = itemview.findViewById(R.id.title_desc);
            offer_from = itemview.findViewById(R.id.offer_from);
            offer_valid = itemview.findViewById(R.id.offer_valid);
            appCompatButton = itemview.findViewById(R.id.appCompatButton);
            networkImageView = itemview.findViewById(R.id.networkImageView);
            cardview = itemview.findViewById(R.id.cardview);
        }


    }
}