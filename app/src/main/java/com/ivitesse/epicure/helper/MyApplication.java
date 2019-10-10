package com.ivitesse.epicure.helper;

import android.app.Application;

import androidx.annotation.NonNull;

public class MyApplication extends Application {
    private static MyApplication mInstance;

    @NonNull
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }


    public void setConnectivityListener(@NonNull ConnectivityChangeReceiver.ConnectivityReceiverListener listener) {
        ConnectivityChangeReceiver.connectivityReceiverListener = listener;
    }
}