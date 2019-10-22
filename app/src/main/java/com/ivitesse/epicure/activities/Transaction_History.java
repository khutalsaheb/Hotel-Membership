package com.ivitesse.epicure.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.SortByAdapter;
import com.ivitesse.epicure.adapter.TransactionHistoryAdapter;
import com.ivitesse.epicure.helper.ConfigUrl;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.helper.SessionManager;
import com.ivitesse.epicure.model.EpiModel;
import com.ivitesse.epicure.volleydata.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Transaction_History extends BaseActivity implements ConnectivityChangeReceiver.ConnectivityReceiverListener,
        SortByAdapter.DetailTimeListener {
    FloatingActionButton fab;
    private RecyclerView recyclerview, recyclerViewSort;
    private ArrayList<EpiModel> epiModels;
    private ArrayList<EpiModel> epiModelssort;
    private Toolbar toolbar;
    private BottomSheetBehavior mBottomSheetBehaviour;
    SessionManager sessionManager;
    String userId, Saved, Used;
    AppCompatTextView used_rs, saved_rs;
    String jsonString;
    EpiModel epiModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> users = sessionManager.getUser();
        userId = users.get(SessionManager.KEY_USERID);

        setContentView(R.layout.new_transaction_layout);
        init();
    }


    private void init() {
        toolbar = findViewById(R.id.toolbar);
        inittoolbar();
        epiModels = new ArrayList<>();
        epiModel = new EpiModel();
        recyclerview = findViewById(R.id.recyclerview);
        saved_rs = findViewById(R.id.saved_rs);
        used_rs = findViewById(R.id.used_rs);

        fab = findViewById(R.id.fab);
        AddBottom();
        showLoading();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigUrl.getSavings,
                response -> {
                    hideLoading();

                    try {
                        JSONObject obj = new JSONObject(response);
                        if (!obj.getBoolean("error")) {

                            Saved = obj.getString("totalSaved");
                            Used = obj.getString("totalSpent");
                            JSONArray data = obj.getJSONArray("data");
                            jsonString = data.toString();
                            sessionManager.sorts(jsonString);


                            for (int i = 0; i < data.length(); i++) {
                                epiModel = new EpiModel();
                                JSONObject dataobj = data.getJSONObject(i);
                                epiModel.setTitle(dataobj.getString("hotelName"));
                                epiModel.setTitle_desc(dataobj.getString("discountCouponName"));
                                epiModel.setDt(dataobj.getString("date"));
                                epiModel.setOriginal_value(dataobj.getString("total"));
                                epiModel.setDiscount_value(dataobj.getString("spentTotal"));
                                epiModel.setSaving(dataobj.getString("totalSaving"));

                                epiModels.add(epiModel);
                            }
                            setupRecycler();

                        } else {
                            hideLoading();
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    hideLoading();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userId", userId);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-Api-Key", ConfigUrl.Api_Key);
                return headers;

            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


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
        hideLoading();
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

        setupRecyclerSort();
    }

    private void inittoolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //  getSupportActionBar().setTitle("Transaction History");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setTitle("Transaction History");
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getApplicationContext(), R.color.transperent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
    }


    @SuppressLint("SetTextI18n")
    private void setupRecycler() {

        saved_rs.setText(getString(R.string.Rs) + Saved);
        used_rs.setText(getString(R.string.Rs) + Used);
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
        mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);

        switch (details.getTitle()) {
            case "Date":
                SortData("date");
                break;
            case "Discount":
                SortData("totalSaving");
                break;
            case "Hotel- A to Z":
                SortData("hotelName");
                break;

        }
    }

    private void SortData(String KEY_NAME) {
        epiModels.clear();
        HashMap<String, String> users = sessionManager.setsorts();
        String jsonString = users.get(SessionManager.KEY_SORT);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonValues.add(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(jsonValues, (a, b) -> {
            String valA = new String();
            String valB = new String();

            try {
                valA = (String) a.get(KEY_NAME);
                valB = (String) b.get(KEY_NAME);
            } catch (JSONException e) {
                //do something
            }

            return valA.compareTo(valB);
            //if you want to change the sort order, simply use the following:
            //return -valA.compareTo(valB);
        });

        for (int i = 0; i < jsonArray.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }


        for (int i = 0; i < sortedJsonArray.length(); i++) {

            try {
                epiModel = new EpiModel();
                JSONObject jsonObject = sortedJsonArray.getJSONObject(i);
                epiModel.setTitle(jsonObject.getString("hotelName"));
                epiModel.setTitle_desc(jsonObject.getString("discountCouponName"));
                epiModel.setDt(jsonObject.getString("date"));
                epiModel.setOriginal_value(jsonObject.getString("total"));
                epiModel.setDiscount_value(jsonObject.getString("spentTotal"));
                epiModel.setSaving(jsonObject.getString("totalSaving"));

                epiModels.add(epiModel);

                setupRecycler();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


}
