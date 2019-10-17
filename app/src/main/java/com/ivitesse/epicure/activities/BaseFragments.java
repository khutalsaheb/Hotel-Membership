package com.ivitesse.epicure.activities;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.dialogs.CustomProgressDialog;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.squareup.picasso.Picasso;

public class BaseFragments extends DialogFragment {
    private BroadcastReceiver mNetworkReceiver;

    private CustomProgressDialog customProgressDialog;

    void Checkit() {

        mNetworkReceiver = new ConnectivityChangeReceiver();
        getActivity().registerReceiver(mNetworkReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION
        ));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getActivity().registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }


    void showNetworkMessage(boolean isConnected) {
        if (!isConnected) {
            getActivity().setContentView(R.layout.activity_main);
            MaterialButton retry = getActivity().findViewById(R.id.retry);
            AppCompatImageView image = getActivity().findViewById(R.id.image);
            Picasso.get()
                    .load(R.drawable.no_internet_connection_found)
                    .into(image);
            retry.setOnClickListener(v -> getActivity().recreate());

        }
    }


    private void unregisterNetworkChanges() {
        try {
            getActivity().unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void showLoading() {
        hideLoading();
        if (customProgressDialog == null) {
            customProgressDialog = new CustomProgressDialog();
        }
        try {
            customProgressDialog.show(getActivity().getSupportFragmentManager().beginTransaction(), "");
        } catch (Exception ignored) {

        }
    }


    public void hideLoading() {
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