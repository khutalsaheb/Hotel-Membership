package com.ivitesse.epicure.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.RatingReviewsAdapter;
import com.ivitesse.epicure.dialogs.Rating_Review_Fragment;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.model.EpiModel;

import java.util.ArrayList;
import java.util.Objects;

public class Review_Rating extends BaseActivity implements ConnectivityChangeReceiver.ConnectivityReceiverListener {
    private ArrayList<EpiModel> epiModels;
    RecyclerView recyclerview;
    private Toolbar toolbar;
    private ProgressDialog pDialog;
    ExtendedFloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        setContentView(R.layout.activity_review_rating);
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
        getSupportActionBar().setTitle("Rating and Reviews");
        recyclerview = findViewById(R.id.recyclerview);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> Rating_Review_Fragment.display(getSupportFragmentManager()));
        epiModels = new ArrayList<>();
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        epiModels.add(new EpiModel("Get 1 Buy 2 dish in Dinner", "this is new offer for our customers", "hotel crown pune", "12 nov,2019"));
        setupRecycler();


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


    private void setupRecycler() {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(recyclerLayoutManager);
        RatingReviewsAdapter recyclerViewAdapter = new RatingReviewsAdapter(epiModels, this);
        recyclerview.setAdapter(recyclerViewAdapter);

    }


}
