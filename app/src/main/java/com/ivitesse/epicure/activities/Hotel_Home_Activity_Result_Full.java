package com.ivitesse.epicure.activities;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.adapter.Amenities_Adapter;
import com.ivitesse.epicure.dialogs.Booking_Form_Fragment;
import com.ivitesse.epicure.helper.StickyBottomBehavior;
import com.ivitesse.epicure.model.Models_Hotel_Booking;

import java.util.ArrayList;
import java.util.Objects;

public class Hotel_Home_Activity_Result_Full extends BaseActivity implements View.OnClickListener {

    private final ArrayList<Models_Hotel_Booking> data = new ArrayList<>();
    private AppCompatTextView hotelname;
    private AppCompatTextView hotellocation;
    private AppCompatTextView hotelpolicies;
    private AppCompatTextView txt_placeprice;
    private AppCompatTextView txt_offer_deal;
    private AppCompatRatingBar rating;
    private AppCompatImageView location_image;
    private AppCompatButton my_sticky_button;
    private AppCompatButton book_hotel;
    private RecyclerView recyclerView;
    private AppCompatImageButton likebutton, likebuttonok;
    private BottomSheetDialog bottomSheetDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_home_result_full);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        hotelname = findViewById(R.id.hotelname);
        hotellocation = findViewById(R.id.hotellocation);
        hotelpolicies = findViewById(R.id.hotelpolicies);
        txt_placeprice = findViewById(R.id.txt_placeprice);
        txt_offer_deal = findViewById(R.id.txt_offer_deal);
        rating = findViewById(R.id.rating);
        location_image = findViewById(R.id.location_image);
        recyclerView = findViewById(R.id.recyclerview);


        likebutton = findViewById(R.id.likebutton);
        likebuttonok = findViewById(R.id.likebuttonok);
        likebutton.setOnClickListener(this);
        likebuttonok.setOnClickListener(this);

        //   View anchor = findViewById(R.id.anchorview);
        my_sticky_button = findViewById(R.id.my_sticky_button);
        my_sticky_button.setOnClickListener(this);
        ((CoordinatorLayout.LayoutParams) my_sticky_button.getLayoutParams()).setBehavior(new StickyBottomBehavior(R.id.anchorview, getResources().getDimensionPixelOffset(R.dimen._15sdp)));


        hotelname.setText("The Orchid Hotel, Hinjewadi, Pune");
        hotellocation.setText("Adjacent to Chhatrapati Shivaji Sports Complex, Pune-Bangalore Highway, Hinjewadi, Pune");
        location_image.setImageResource(R.drawable.hotel_image);
        txt_placeprice.setText("$2000");
        rating.setRating((float) 4.6);
        rating.setNumStars(5);
        rating.setStepSize((float) 0.1);
        hotelpolicies.setText("Non Smoking Hotel\n" +
                "There are no restrictions on alcohol consumption.\n" +
                "Caretaker does not stay on the property\n" +
                "There are no restrictions on food for guests\n" +
                "There are no pets living on the property\n" +
                "Kitchen access (without basic utensils)\n" +
                "Pets are not allowed.\n" +
                "Credit/debit cards are accepted\n" +
                "Guests wont have to climb stairs to reach the room\n" +
                "Does not allow private parties or events\n" +
                "GUEST PROFILE\n" +
                "With valid govt photo id (Aadhar card/ Driving Licence/ Election Card/ Valid passport)\n" +
                "Bachelors allowed");
        txt_offer_deal.setText("Popular Choice! Booked by 13 people yesterday,Only 19 km from Pune International Airport, the hotel is also within close distance from University of Pune (10 km) and Fergusson College (13 km).");
        txt_offer_deal.setSelected(true);
        GetExplore();


    }


    private void GetExplore() {
        data.add(new Models_Hotel_Booking("WiFi", R.drawable.hotel_image));
        data.add(new Models_Hotel_Booking("Swimming Pool", R.drawable.hotel_image));
        data.add(new Models_Hotel_Booking("Bakery", R.drawable.hotel_image));
        data.add(new Models_Hotel_Booking("Parking", R.drawable.hotel_image));
        data.add(new Models_Hotel_Booking("Power Backup", R.drawable.hotel_image));
        data.add(new Models_Hotel_Booking("Fitness Centre", R.drawable.hotel_image));
        // data.add(new Models("More", R.drawable.ic_more_horiz));

        recyclerView.setHasFixedSize(true);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }

        Amenities_Adapter adapter = new Amenities_Adapter(this, data);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(@Nullable View v) {
        switch (v.getId()) {
            case R.id.likebutton:
                likebuttonok.setVisibility(View.VISIBLE);
                likebutton.setVisibility(View.INVISIBLE);
                break;
            case R.id.my_sticky_button:
                Booking_Form_Fragment.display(getSupportFragmentManager());
                // BookMyHotel();
                break;
            case R.id.book_hotel:
                Toast.makeText(getApplicationContext(), "Booking Sucessfully Done", Toast.LENGTH_LONG).show();
                bottomSheetDialog.dismiss();
                break;

            case R.id.likebuttonok:
                likebutton.setVisibility(View.VISIBLE);
                likebuttonok.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void BookMyHotel() {

        @SuppressLint("InflateParams") final View bootomNavigation = getLayoutInflater().inflate(R.layout.activity_hotel_booking, null);
        bottomSheetDialog = new BottomSheetDialog(Hotel_Home_Activity_Result_Full.this);
        bottomSheetDialog.setContentView(bootomNavigation);
        bottomSheetDialog.show();
        book_hotel = bootomNavigation.findViewById(R.id.book_hotel);
        book_hotel.setOnClickListener(this);
    }



  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void menuOpen() {
        //   showToast("Menu Open");

    }

    @Override
    public void menuClose() {//showToast( "Menu Close");
    }

    @Override
    public void menuItemClicked(int menuNumber) {
        // showToast( "Menu item clicked : " + menuNumber);

    }

    private void showToast(String msg) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.END, 0, 0);
        mToast.show();
    }*/
}
