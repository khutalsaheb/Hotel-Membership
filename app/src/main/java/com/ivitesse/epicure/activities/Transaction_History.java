package com.ivitesse.epicure.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.SortByAdapter;
import com.ivitesse.epicure.adapter.TransactionHistoryAdapter;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.model.EpiModel;

import java.util.ArrayList;
import java.util.Objects;

public class Transaction_History extends BaseActivity implements ConnectivityChangeReceiver.ConnectivityReceiverListener, SortByAdapter.DetailTimeListener {
    FloatingActionButton fab;
    private RecyclerView recyclerview, recyclerViewSort;
    private ArrayList<EpiModel> epiModels;
    private ArrayList<EpiModel> epiModelssort;
    private Toolbar toolbar;
    private BottomSheetBehavior mBottomSheetBehaviour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        setContentView(R.layout.new_transaction_layout);
        init();

    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        inittoolbar();


        recyclerview = findViewById(R.id.recyclerview);

        fab = findViewById(R.id.fab);
        epiModels = new ArrayList<>();
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "GET 1 Buy 1 offer", "Rs. 2000", "Rs. 1500", "Save Rs. 500"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj Prime Membership Renewal Offer", "GET 1 Buy 1 offer", "Rs. 2000", "Rs. 1500", "Save Rs. 500"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "GET 1 Buy 1 offer", "Rs. 2000", "Rs. 1500", "Save Rs. 500"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", ". With the Renewal Offer, get Rs. 200 cashback on your first order of Rs. 1000 & above.", "Rs. 2000", "Rs. 1500", "Save 10%"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "GET 1 Buy 1 offer", "Rs. 2000", "Rs. 1500", "Save Rs. 500"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj Prime Membership Renewal Offer", "GET 1 Buy 1 offer", "Rs. 2000", "Rs. 1500", "Save Rs. 500"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "GET 1 Buy 1 offer", "Rs. 2000", "Rs. 1500", "Save Rs. 500"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", ". With the Renewal Offer, get Rs. 200 cashback on your first order of Rs. 1000 & above.", "Rs. 2000", "Rs. 1500", "Save 10%"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "GET 1 Buy 1 offer", "Rs. 2000", "Rs. 1500", "Save Rs. 500"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj Prime Membership Renewal Offer", "GET 1 Buy 1 offer", "Rs. 2000", "Rs. 1500", "Save Rs. 500"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", "GET 1 Buy 1 offer", "Rs. 2000", "Rs. 1500", "Save Rs. 500"));
        epiModels.add(new EpiModel("11 May", "2019", "Hotel Taj", ". With the Renewal Offer, get Rs. 200 cashback on your first order of Rs. 1000 & above.", "Rs. 2000", "Rs. 1500", "Save 10%"));

        setupRecycler();
        AddBottom();

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

    @SuppressLint("InflateParams")
    private void AddBottom() {
        recyclerViewSort = findViewById(R.id.recyclerview11);
        fab.setOnClickListener(v -> mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED));
        View nestedScrollView = findViewById(R.id.nestedScrollView);
        mBottomSheetBehaviour = BottomSheetBehavior.from(nestedScrollView);
        mBottomSheetBehaviour.setPeekHeight(0);    //Set the peek height

        epiModelssort = new ArrayList<>();
        epiModelssort.add(new EpiModel("Date"));
        epiModelssort.add(new EpiModel("Discount"));
        epiModelssort.add(new EpiModel("Hotel- A to Z"));
        epiModelssort.add(new EpiModel("Hotel- Z to A"));

        setupRecyclerSort();
    }

    private void inittoolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Transaction History");
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
    }


    private void setupRecycler() {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
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
        TransactionHistoryAdapter recyclerViewAdapter = new TransactionHistoryAdapter(epiModels, this);
        recyclerview.setAdapter(recyclerViewAdapter);

    }

    private void setupRecyclerSort() {

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerViewSort.setLayoutManager(recyclerLayoutManager);
        SortByAdapter recyclerViewAdapter = new SortByAdapter(epiModelssort, this, this);
        recyclerViewSort.setAdapter(recyclerViewAdapter);
        // notify adapter that data has changed
        recyclerViewAdapter.notifyDataSetChanged();
    }

 /*   @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sort_transaction, menu);
        return true;
    }

    @SuppressLint("InflateParams")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort) {
            bootomNavigation = getLayoutInflater().inflate(R.layout.bottomsheet_layout, null);
            bottomSheetDialog = new BottomSheetDialog(Transaction_History.this);
            bottomSheetDialog.setContentView(bootomNavigation);
            bottomSheetDialog.show();
            recyclerViewSort = bootomNavigation.findViewById(R.id.recyclerview);
            epiModelssort = new ArrayList<>();
            epiModelssort.add(new EpiModel("Discount"));
            epiModelssort.add(new EpiModel("Hotel- A to Z"));
            epiModelssort.add(new EpiModel("Hotel- Z to A"));

            setupRecyclerSort();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void OnClickDetailsListener(@NonNull EpiModel details) {
        Toast.makeText(getApplicationContext(),
                "selected offer is " + details.getTitle(),
                Toast.LENGTH_LONG).show();
        mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }
}
