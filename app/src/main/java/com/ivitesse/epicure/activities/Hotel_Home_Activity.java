package com.ivitesse.epicure.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ivitesse.epicure.R;

import java.util.Objects;

public class Hotel_Home_Activity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    private final String[] users = {"Mehendi/ Sangeet", "Anniversary", "Office party", "Birthdays",
            "Corporate Events", "Baby Shower", "Get Together", "Wedding", "Cocktails", "Reception"};
    private MaterialButton search_buses;
    private TextInputEditText search_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hotel_home);
        search_buses = findViewById(R.id.search_buses);
        search_text = findViewById(R.id.search_text);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        String search = getIntent().getStringExtra("key");
        search_text.setText(search);
        search_buses.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Hotel_Home_Activity_Result.class);
            startActivity(intent);
        });


        Spinner spin = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(@NonNull AdapterView<?> arg0, @NonNull View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), "Selected User: " + users[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(@NonNull AdapterView<?> arg0) {

    }
}



