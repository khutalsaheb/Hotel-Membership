package com.ivitesse.epicure.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.ivitesse.epicure.R;

public class Login_Activity extends AppCompatActivity {
     private View bootomNavigation;
     private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
    }

    public void login(@Nullable View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void newuser(@Nullable View view) {
        startActivity(new Intent(getApplicationContext(), Registration_Activity.class));
    }

    @SuppressLint("InflateParams")
    public void forgotpassword(@Nullable View view) {
        bootomNavigation = getLayoutInflater().inflate(R.layout.forgot_password_bottom, null);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bootomNavigation);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(true);
    }

    public void forgotaccess(View view) {
        bottomSheetDialog.dismiss();
    }
}
