package com.ivitesse.epicure.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ivitesse.epicure.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Offer_Details_coupons extends AppCompatActivity {
    AppCompatImageView networkImageView;
    AppCompatTextView offer_from, title, title_desc, offer_desc, offer_valid;
    private Toolbar toolbar;
    private String discountCouponName, expiry_date, image, hotelName, description, termsandconditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer__details_coupons);
        toolbar = findViewById(R.id.toolbar);
        inittoolbar();
        GetBundles();
        initUI();





        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void GetBundles() {
        Bundle bundle = getIntent().getExtras();

        discountCouponName = bundle.getString("discountCouponName");
        expiry_date = bundle.getString("expiry_date");
        image = bundle.getString("image");
        hotelName = bundle.getString("hotelName");
        description = bundle.getString("description");
        termsandconditions = bundle.getString("termsandconditions");

    }

    private void inittoolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);  //  getSupportActionBar().setTitle("Transaction History");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setTitle("Offer Details");
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getApplicationContext(), R.color.transperent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

    }

    @SuppressLint("SetTextI18n")
    private void initUI() {
        title = findViewById(R.id.title);
        title_desc = findViewById(R.id.title_desc);
        offer_from = findViewById(R.id.offer_from);
        offer_valid = findViewById(R.id.offer_valid);
        networkImageView = findViewById(R.id.networkImageView);
        offer_desc = findViewById(R.id.offer_desc);
        Picasso.get().load(image).fit().centerCrop().into(networkImageView);
        title.setText(discountCouponName);
        title_desc.setText(description);
        offer_valid.setText("Offer Expires " + expiry_date);
        offer_from.setText(hotelName);
        offer_desc.setText(termsandconditions);
    }

}
