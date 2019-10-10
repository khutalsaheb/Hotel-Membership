package com.ivitesse.epicure.activities;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.squareup.picasso.Picasso;

public class BaseActivity extends AppCompatActivity {
    private BroadcastReceiver mNetworkReceiver;


    void Checkit() {
        mNetworkReceiver = new ConnectivityChangeReceiver();
        registerReceiver(mNetworkReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION
        ));

        registerNetworkBroadcastForNougat();
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


    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    private void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}