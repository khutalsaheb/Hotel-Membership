package com.ivitesse.epicure.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.FutureCouponsAdapter;
import com.ivitesse.epicure.helper.CenterZoomLayoutManager;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.model.EpiModel;

import java.util.ArrayList;
import java.util.Objects;

public class Future_Coupons extends BaseActivity implements ConnectivityChangeReceiver.ConnectivityReceiverListener {
    ArrayList<EpiModel> epiModels;
    private RecyclerView recyclerview;
    private Toolbar toolbar;
    private ProgressDialog pDialog;
    AppCompatTextView count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        setContentView(R.layout.recyclerviewcoupons);
        init();

    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Future Coupons");
        recyclerview = findViewById(R.id.recyclerview);
        count = findViewById(R.id.count);

        epiModels = new ArrayList<>();
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", getString(R.string.large_text),
                "hotel crown pune", "12 nov,2019"));
        // setupRecycler();
        onSetRecyclerView();

        /*    pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConfigUrl.getTermsAndRules,
                response -> {
                    hidePDialog();

                    try {
                        JSONObject obj = new JSONObject(response);
                        if (!obj.getBoolean("error")) {
                            epiModels = new ArrayList<>();
                            JSONArray data = obj.getJSONArray("data");

                            for (int i = 0; i < data.length(); i++) {

                                EpiModel epiModel = new EpiModel();
                                JSONObject dataobj = data.getJSONObject(i);
                                epiModel.setName(dataobj.getString("name"));
                                epiModel.setDescription(dataobj.getString("description"));

                                epiModels.add(epiModel);
                            }
                            setupRecycler();

                        } else {
                            hidePDialog();
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    hidePDialog();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-Api-Key", ConfigUrl.Api_Key);
                return headers;

            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }*/
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showNetworkMessage(isConnected);
    }


    @Override
    protected void onResume() {

        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private void onSetRecyclerView() {
        CenterZoomLayoutManager layoutManager =
                new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerview.setLayoutManager(layoutManager);
        FutureCouponsAdapter recyclerViewAdapter = new FutureCouponsAdapter(epiModels, this);
        recyclerview.setAdapter(recyclerViewAdapter);
        // Scroll to the position we want to snap to
        layoutManager.scrollToPosition(epiModels.size() / 2);
        // Wait until the RecyclerView is laid out.

        recyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int positionView = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                int lastpositionView = epiModels.size();
                count.setText((positionView + 1) + " / " + lastpositionView); //The TextView you want to update
            }
        });
        recyclerview.post(() -> {
            // Shift the view to snap  near the center of the screen.
            // This does not have to be precise.
            int dx = (recyclerview.getWidth() - recyclerview.getChildAt(0).getWidth()) / 2;
            recyclerview.scrollBy(-dx, 0);
            // Assign the LinearSnapHelper that will initially snap the near-center view.
            LinearSnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(recyclerview);


        });
    }


}
