package com.ivitesse.epicure.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.RatingReviewsAdapter;
import com.ivitesse.epicure.dialogs.Rating_Review_Fragment;
import com.ivitesse.epicure.helper.ConfigUrl;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.model.EpiModel;
import com.ivitesse.epicure.volleydata.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Review_Rating extends BaseActivity implements ConnectivityChangeReceiver.ConnectivityReceiverListener {
    private ArrayList<EpiModel> epiModels;
    private RecyclerView recyclerview;
    private Toolbar toolbar;
    ExtendedFloatingActionButton fab;
    AppCompatImageView appCompatImageView;

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
        appCompatImageView = findViewById(R.id.appCompatImageView);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> Rating_Review_Fragment.display(getSupportFragmentManager()));

        showLoading();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConfigUrl.getRatingAndReviews,
                response -> {
                    hideLoading();

                    try {
                        JSONObject obj = new JSONObject(response);
                        if (!obj.getBoolean("error")) {
                            if (obj.getString("message").equals("Record not found.")) {
                                appCompatImageView.setVisibility(View.VISIBLE);
                                recyclerview.setVisibility(View.GONE);
                            } else {
                                epiModels = new ArrayList<>();
                                JSONArray data = obj.getJSONArray("data");
                                for (int i = 0; i < data.length(); i++) {
                                    EpiModel epiModel = new EpiModel();
                                    JSONObject dataobj = data.getJSONObject(i);
                                    epiModel.setName(dataobj.getString("name"));
                                    epiModel.setReview(dataobj.getString("review"));
                                    epiModel.setProfile_pic(dataobj.getString("profile_pic"));
                                    epiModel.setRating(dataobj.getString("rating"));
                                    epiModels.add(epiModel);
                                }
                                setupRecycler();

                            }
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
        RatingReviewsAdapter recyclerViewAdapter = new RatingReviewsAdapter(epiModels, this);
        recyclerview.setAdapter(recyclerViewAdapter);

    }


}
