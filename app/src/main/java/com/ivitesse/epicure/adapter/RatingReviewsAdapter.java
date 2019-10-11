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


public class RatingReviewsAdapter extends RecyclerView.Adapter<RatingReviewsAdapter.ViewHolder> {

    private final List<EpiModel> offersList;
    private final Context context;

    public RatingReviewsAdapter(@NonNull List<EpiModel> offersListIn, @NonNull Context ctx) {
        offersList = offersListIn;
        context = ctx;
    }

    @NonNull
    @Override
    public RatingReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                              int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rating_reviews, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingReviewsAdapter.ViewHolder holder,
                                 int position) {
        EpiModel offersModel = offersList.get(position);
        holder.title.setText(offersModel.getTitle());
        holder.title_review.setText(offersModel.getDescription());

    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AppCompatTextView title, title_review;

        ViewHolder(View itemview) {
            super(itemview);
            title = itemview.findViewById(R.id.title);
            title_review = itemview.findViewById(R.id.title_review);
        }


    }
}