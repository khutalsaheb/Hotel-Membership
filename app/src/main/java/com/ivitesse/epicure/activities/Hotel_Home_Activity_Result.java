package com.ivitesse.epicure.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.Hotel_Home_Adapter;
import com.ivitesse.epicure.model.Models_Hotel_Booking;

import java.util.ArrayList;
import java.util.Objects;

public class Hotel_Home_Activity_Result extends BaseActivity implements Hotel_Home_Adapter.DetailsListener {
    private final ArrayList<Models_Hotel_Booking> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hotel_home_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        data.add(new Models_Hotel_Booking("111", "The Orchid Hotel, Hinjewadi, Pune", "4.2",
                R.drawable.hotel_image, "$2000", "Popular Choice! Booked by 13 people yesterday,Only 19 km from Pune International Airport, the hotel is also within close distance from University of Pune (10 km) and Fergusson College (13 km)."));
        data.add(new Models_Hotel_Booking("99", "Mezza 9",
                "4.3", R.drawable.hotel_image, "$2057", "Free Drop"));
        data.add(new Models_Hotel_Booking("102", "Hotel Pune", "4.4",
                R.drawable.hotel_image, "$5000", "Discount on Rooms"));
        data.add(new Models_Hotel_Booking("105", "Ginger Pune Wakad", "5",
                R.drawable.hotel_image, "$2756", "Free Pass"));
        data.add(new Models_Hotel_Booking("80", "Hotel Sayaji", "3.2",
                R.drawable.hotel_image, "$2000", "Discount on Meals"));
        data.add(new Models_Hotel_Booking("100", "Royal Orchid Golden Suite Pune", "4.0",
                R.drawable.hotel_image, "$555", "Free meals"));
        data.add(new Models_Hotel_Booking("101", "Royal Orchid Central", "4.7",
                R.drawable.hotel_image, "$3214", "Min 15 % Dsicount on rooms"));
        data.add(new Models_Hotel_Booking("98", "Siesta Hinjewadi", "4.1",
                R.drawable.hotel_image, "$1000", "25% off"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Hotel_Home_Adapter adapter = new Hotel_Home_Adapter(data, this);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void DealsDetailsListener(@Nullable Models_Hotel_Booking details) {
        Intent intent = new Intent(getApplicationContext(), Hotel_Home_Activity_Result_Full.class);
        startActivity(intent);
    }
}
