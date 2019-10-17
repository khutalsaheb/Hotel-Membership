package com.ivitesse.epicure.activities;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.Past_Stays_Adapter;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.model.EpiModel;

import java.util.ArrayList;
import java.util.Objects;

public class Past_Stays extends BaseActivity implements ConnectivityChangeReceiver.ConnectivityReceiverListener {
    private RecyclerView recyclerview;
    private ArrayList<EpiModel> epiModels;
    private Toolbar toolbar;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        setContentView(R.layout.recyclerview);
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
        getSupportActionBar().setTitle("Past Stays");
        recyclerview = findViewById(R.id.recyclerview);
        epiModels = new ArrayList<>();
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "33% off on 5 people ", "Rs. 2000", "5 Guest ", "Save Rs. 500", "Pune Banglore highway 411000"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "33% off on 5 people ", "Rs. 2000", "Party", "Save Rs. 500", "Pune Banglore highway 411000"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "33% off on 5 people ", "Rs. 2000", "Min 5 Guest ", "Save Rs. 500", "Pune Banglore highway 411000"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "33% off on 5 people ", "Rs. 2000", "Min 5 peoples ", "Save Rs. 500", "Pune Banglore highway 411000"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "33% off on 5 people ", "Rs. 2000", "Min 5 Guest ", "Save Rs. 500", "Pune Banglore highway 411000"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "33% off on 5 people ", "Rs. 2000", "Min 5 peoples ", "Save Rs. 500", "Pune Banglore highway 411000"));

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

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(recyclerLayoutManager);
        Past_Stays_Adapter recyclerViewAdapter = new Past_Stays_Adapter(epiModels, this);
        recyclerview.setAdapter(recyclerViewAdapter);

    }


}
