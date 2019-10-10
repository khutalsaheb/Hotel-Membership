package com.ivitesse.epicure.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.helper.SessionManager;

import java.util.HashMap;
import java.util.Objects;

public class Membership_Activity extends AppCompatActivity implements View.OnClickListener {
    private final static int QRcodeWidth = 100;
    private SessionManager sessionManager;
    private AppCompatTextView mem_id;
    private AppCompatTextView mem_type;
    private AppCompatTextView mem_name;
    private AppCompatTextView mem_valid;
    private String userId;
    private String membership_id;
    private String email;
    private String name;
    private String mobile;
    private String profile_pic_url;
    private String dob_data;
    private String anniversary_date;
    private String address;
    private Bitmap bitmap;
    private AppCompatImageView imageView;
    private CardView cardviewfull;
    private CardView cardview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_design);
        sessionManager = new SessionManager(getApplicationContext());
        getDetails();

        init();
    }

    private void getDetails() {
        HashMap<String, String> users = sessionManager.getUser();
        userId = users.get(SessionManager.KEY_USERID);
        membership_id = users.get(SessionManager.KEY_UID);
        email = users.get(SessionManager.KEY_EMAIL);
        name = users.get(SessionManager.KEY_NAME);
        mobile = users.get(SessionManager.KEY_MOBILE);
        dob_data = users.get(SessionManager.KEY_DOB);
        anniversary_date = users.get(SessionManager.KEY_ANIDATE);
        address = users.get(SessionManager.KEY_ADDRESS);
        profile_pic_url = users.get(SessionManager.KEY_PICID);

    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Your Membership Details");
        imageView = findViewById(R.id.imageView);

        cardview = findViewById(R.id.cardview);
        cardviewfull = findViewById(R.id.cardviewfull);
        cardview.setOnClickListener(this);
        cardviewfull.setOnClickListener(this);

        try {
            bitmap = TextToImageEncode(membership_id);
            imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        mem_id = findViewById(R.id.mem_id);
        mem_type = findViewById(R.id.mem_type);
        mem_name = findViewById(R.id.mem_name);
        mem_valid = findViewById(R.id.mem_valid);
        mem_id.setText(membership_id);
        mem_name.setText(name);

    }

    private Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.QR_CODE, QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black) : getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 100, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }


    @Override
    public void onClick(@NonNull View v) {
        switch (v.getId()) {
            case R.id.cardviewfull:
                cardview.setVisibility(View.VISIBLE);
                cardviewfull.setVisibility(View.GONE);
                break;
            case R.id.cardview:
                cardviewfull.setVisibility(View.VISIBLE);
                cardview.setVisibility(View.GONE);
                break;

        }
    }
}

