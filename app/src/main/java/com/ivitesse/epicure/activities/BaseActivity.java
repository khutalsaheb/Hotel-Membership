package com.ivitesse.epicure.activities;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.dialogs.CustomProgressDialog;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.squareup.picasso.Picasso;

import java.io.File;

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

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
        hideLoading();
        clearApplicationData();
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "File /data/data/APP_PACKAGE/" + s + " DELETED");
                }
            }
        }
    }
    /*  public static void deleteCache(@NonNull Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception ignored) {}
    }
    public static boolean deleteDir(@NonNull File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }*/
}