package com.ivitesse.epicure.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.Deals_Adapter;
import com.ivitesse.epicure.adapter.Discover_Adapter;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.model.Models_Hotel_Booking;

import java.util.ArrayList;
import java.util.Objects;

public class Futures_Booking extends BaseActivity implements View.OnClickListener,
        Discover_Adapter.DiscoverDetailsListener, Deals_Adapter.DetailsListener, ConnectivityChangeReceiver.ConnectivityReceiverListener {

    private final ArrayList<Models_Hotel_Booking> data = new ArrayList<>();
    private final ArrayList<Models_Hotel_Booking> dataModels_hotel_bookings = new ArrayList<>();
    private MaterialButton search_buses;
    private TextInputEditText edit_search;
    private String search;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        setContentView(R.layout.activity_hotel_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        edit_search = findViewById(R.id.edit_search);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        data.add(new Models_Hotel_Booking("Nature Theatre of Costa Rica",
                "Costa Rica",
                "4.1",
                "1000",
                "$224", R.drawable.hotel_image));
        data.add(new Models_Hotel_Booking("Nature Theatre of Costa Rica",
                "Costa Rica",
                "4.1",
                "957",
                "$354", R.drawable.hotel_image));
        data.add(new Models_Hotel_Booking("Nature Theatre of Costa Rica",
                "Costa Rica",
                "4.1",
                "555",
                "$454", R.drawable.hotel_image));
        data.add(new Models_Hotel_Booking("Nature Theatre of Costa Rica",
                "Costa Rica",
                "4.1",
                "754",
                "$654", R.drawable.hotel_image));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Discover_Adapter adapter = new Discover_Adapter(this, data, this);
        recyclerView.setAdapter(adapter);

        ///GRID VIWEW
        RecyclerView recyclerViewgrid = findViewById(R.id.recyclerview1);
        dataModels_hotel_bookings.add(new Models_Hotel_Booking("1 day Left", "Elstree Inn", "Dubai", "11 Days 10 Nights", R.drawable.hotel_image, "$2000"));
        dataModels_hotel_bookings.add(new Models_Hotel_Booking("11 day Left", "Elstree Inn", "Turkey", "7 Days 6 Nights", R.drawable.hotel_image, "$2057"));
        dataModels_hotel_bookings.add(new Models_Hotel_Booking("2 day Left", "Elstree Inn", "India", "15 Days 14 Nights", R.drawable.hotel_image, "$5000"));
        dataModels_hotel_bookings.add(new Models_Hotel_Booking("5 day Left", "Elstree Inn", "Dubai", "11 Days 10 Nights", R.drawable.hotel_image, "$2756"));


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerViewgrid.setLayoutManager(layoutManager1);
        recyclerViewgrid.setHasFixedSize(true);

        Deals_Adapter adapter1 = new Deals_Adapter(dataModels_hotel_bookings, this);
        recyclerViewgrid.setAdapter(adapter1);
        search_buses = findViewById(R.id.search_buses);
        search_buses.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hotel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_hotel) {
            startActivity(new Intent(getApplicationContext(), Future_Booking_List.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(@NonNull View v) {
        if (v.getId() == R.id.search_buses) {
            Intent intent = new Intent(this, Hotel_Home_Activity.class);
            search = edit_search.getText().toString().trim();
            intent.putExtra("key", search);
            startActivity(intent);
        }

    }


    @Override
    public void DealsDetailsListener(@Nullable Models_Hotel_Booking details) {
        startActivity(new Intent(getApplicationContext(), Hotel_Home_Activity_Result_Full.class));
    }

    @Override
    public void DiscoverDealsDetailsListener(@Nullable Models_Hotel_Booking details) {
        startActivity(new Intent(getApplicationContext(), Hotel_Home_Activity_Result_Full.class));
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

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showNetworkMessage(isConnected);
    }


    @Override
    protected void onResume() {

        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);

    }

}
