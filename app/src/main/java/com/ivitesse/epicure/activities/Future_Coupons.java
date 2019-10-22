package com.ivitesse.epicure.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.FutureCouponsAdapter;
import com.ivitesse.epicure.helper.CenterZoomLayoutManager;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Future_Coupons extends BaseActivity implements ConnectivityChangeReceiver.ConnectivityReceiverListener, FutureCouponsAdapter.DetailsListener {
    ArrayList<EpiModel> epiModels;
    private RecyclerView recyclerview;
    private Toolbar toolbar;
    AppCompatTextView count;
    SessionManager sessionManager;
    String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        setContentView(R.layout.recyclerviewcoupons);
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> users = sessionManager.getUser();
        userId = users.get(SessionManager.KEY_USERID);
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


        showLoading();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigUrl.getFutureCoupons,
                response -> {
                    hideLoading();

                    try {
                        JSONObject obj = new JSONObject(response);
                        if (!obj.getBoolean("error")) {
                            epiModels = new ArrayList<>();
                            JSONArray data = obj.getJSONArray("data");

                            for (int i = 0; i < data.length(); i++) {
                                EpiModel epiModel = new EpiModel();
                                JSONObject dataobj = data.getJSONObject(i);
                                epiModel.setTitle(dataobj.getString("discountCouponName"));
                                epiModel.setDescription(dataobj.getString("description"));
                                epiModel.setOffer_from(dataobj.getString("hotelName"));
                                epiModel.setProfile_pic(dataobj.getString("image"));
                                epiModel.setOffer_valid(dataobj.getString("expiry_date"));
                                epiModel.setReview(dataobj.getString("termsandconditions"));

                                epiModels.add(epiModel);
                            }
                            onSetRecyclerView();

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


    private void onSetRecyclerView() {
        CenterZoomLayoutManager layoutManager =
                new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerview.setLayoutManager(layoutManager);
        FutureCouponsAdapter recyclerViewAdapter = new FutureCouponsAdapter(epiModels, this, this);
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

    @Override
    public void DealsDetailsListener(@Nullable EpiModel details) {
//Create the bundle
        Intent i = new Intent(getApplicationContext(), Offer_Details_coupons.class);
        Bundle bundle = new Bundle();
//Add your data from getFactualResults method to bundle
        bundle.putString("discountCouponName", details.getTitle());
        bundle.putString("expiry_date", details.getOffer_valid());
        bundle.putString("image", details.getProfile_pic());
        bundle.putString("hotelName", details.getOffer_from());
        bundle.putString("description", details.getDescription());
        bundle.putString("termsandconditions", details.getReview());
//Add the bundle to the intent String discountCouponName,expiry_date,image,hotelName,description,termsandconditions;
        i.putExtras(bundle);
        startActivity(i);
    }
}
