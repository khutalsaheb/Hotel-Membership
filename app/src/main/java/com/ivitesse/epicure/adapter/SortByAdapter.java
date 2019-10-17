package com.ivitesse.epicure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.model.EpiModel;

import java.util.List;


public class SortByAdapter extends RecyclerView.Adapter<SortByAdapter.ViewHolder> {

    private final List<EpiModel> offersList;
    private final Context context;
    private int lastSelectedPosition = 0;
    private DetailTimeListener itemPresss;

    public SortByAdapter(@NonNull List<EpiModel> offersListIn, @NonNull Context ctx, @NonNull DetailTimeListener itemPress) {
        itemPresss = itemPress;
        offersList = offersListIn;
        context = ctx;
    }

    @NonNull
    @Override
    public SortByAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                       int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottom_filter_by, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SortByAdapter.ViewHolder holder,
                                 int position) {
        EpiModel offersModel = offersList.get(position);
        holder.title.setText(offersModel.getTitle());
        holder.selectionState.setChecked(lastSelectedPosition == position);
        holder.selectionState.setOnClickListener(view -> {
            itemPresss.OnClickDetailsListener(offersModel);
            lastSelectedPosition = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    public interface DetailTimeListener {
        void OnClickDetailsListener(@NonNull EpiModel details);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final AppCompatTextView title;
        final RadioButton selectionState;

        ViewHolder(View itemview) {
            super(itemview);
            title = itemview.findViewById(R.id.title);
            selectionState = itemview.findViewById(R.id.selected);


        }


    }
}