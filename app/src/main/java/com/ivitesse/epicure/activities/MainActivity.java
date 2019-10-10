package com.ivitesse.epicure.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.helper.ConfigUrl;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.helper.SessionManager;
import com.ivitesse.epicure.volleydata.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, ConnectivityChangeReceiver.ConnectivityReceiverListener {

    private CardView profile;
    private CardView about_us;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String userId;
    private String past_coupons;
    private String future_bookings;
    private String future_coupons;
    private String past_ratings_and_reviews;
    private String transaction_history;
    private String past_stays;
    private AppCompatTextView tpast_coupons;
    private AppCompatTextView tfuture_bookings;
    private AppCompatTextView tfuture_coupons;
    private AppCompatTextView tpast_ratings_and_reviews;
    private AppCompatTextView ttransaction_history;
    private AppCompatTextView tpast_stays;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        setContentView(R.layout.dashboard);
        init();
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> users = sessionManager.getUser();
        userId = users.get(SessionManager.KEY_USERID);
        if (userId == null) {
            startActivity(new Intent(getApplicationContext(), Login_Activity.class));
            finish();
        }

    }

    private void init() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tpast_coupons = findViewById(R.id.tpast_coupons);
        tfuture_bookings = findViewById(R.id.tfuture_bookings);
        tfuture_coupons = findViewById(R.id.tfuture_coupons);
        tpast_ratings_and_reviews = findViewById(R.id.tpast_ratings_and_reviews);
        ttransaction_history = findViewById(R.id.ttransaction_history);
        tpast_stays = findViewById(R.id.tpast_stays);

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(() -> getData()
        );
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.YELLOW, Color.BLUE);
        swipeRefreshLayout.setHapticFeedbackEnabled(true);
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Profile_Activity.class)));
        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), About_Info.class)));
        //  getData();

    }

    private void getData() {
        swipeRefreshLayout.setRefreshing(true);
        GetDashboard();
    }

    private void GetDashboard() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigUrl.dashboard,
                response -> {

                    try {
                        JSONObject obj = new JSONObject(response);
                        if (!obj.getBoolean("error")) {
                            JSONObject data = obj.getJSONObject("data");
                            past_coupons = data.getString("past_coupons");
                            future_bookings = data.getString("future_bookings");
                            future_coupons = data.getString("future_coupons");
                            past_stays = data.getString("past_stays");
                            past_ratings_and_reviews = data.getString("past_ratings_and_reviews");
                            transaction_history = data.getString("transaction_history");

// stopping swipe refresh
                            swipeRefreshLayout.setRefreshing(false);

                            tpast_coupons.setText(past_coupons);
                            tfuture_coupons.setText(future_coupons);
                            tfuture_bookings.setText(future_bookings);
                            tpast_stays.setText(past_stays);
                            tpast_ratings_and_reviews.setText(past_ratings_and_reviews);
                            ttransaction_history.setText(transaction_history);
                        } else {
// stopping swipe refresh
                            swipeRefreshLayout.setRefreshing(false);
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
// stopping swipe refresh
                    swipeRefreshLayout.setRefreshing(false);
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
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), Membership_Activity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void onRefresh() {
        getData();
    }
}
