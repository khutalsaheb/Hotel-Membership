package com.ivitesse.epicure.activities;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.dialogs.CustomProgressDialog;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.squareup.picasso.Picasso;

public class BaseActivity extends AppCompatActivity {
    private BroadcastReceiver mNetworkReceiver;

    private CustomProgressDialog customProgressDialog;

    void Checkit() {

        mNetworkReceiver = new ConnectivityChangeReceiver();
        registerReceiver(mNetworkReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION
        ));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }


    void showNetworkMessage(boolean isConnected) {
        if (!isConnected) {
            setContentView(R.layout.activity_main);
            MaterialButton retry = findViewById(R.id.retry);
            AppCompatImageView image = findViewById(R.id.image);
            Picasso.get()
                    .load(R.drawable.no_internet_connection_found)
                    .into(image);
            retry.setOnClickListener(v -> recreate());

        }
    }


    private void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    void showLoading() {
        hideLoading();
        if (customProgressDialog == null) {
            customProgressDialog = new CustomProgressDialog();
        }
        try {
            customProgressDialog.show(getSupportFragmentManager().beginTransaction(), "");
        } catch (Exception ignored) {

        }
    }


    void hideLoading() {
        if (customProgressDialog != null) {
            customProgressDialog.dismiss();
            customProgressDialog.dismissAllowingStateLoss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
        hideLoading();
    }
}