package com.ivitesse.epicure.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.helper.ConfigUrl;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.helper.SessionManager;
import com.ivitesse.epicure.volleydata.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Registration_Activity extends BaseActivity implements ConnectivityChangeReceiver.ConnectivityReceiverListener {
    private AppCompatEditText user_name;
    private AppCompatEditText mobile_number;
    private AppCompatEditText user_email;
    private AppCompatEditText dob;
    private AppCompatEditText password;
    private AppCompatEditText c_password;
    private Calendar c;
    private int mYear;
    private int mMonth;
    private int mDay;
    private SessionManager sessionManager;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Registration Form");
        sessionManager = new SessionManager(getApplicationContext());
        user_name = findViewById(R.id.user_name);
        mobile_number = findViewById(R.id.mobile_number);
        user_email = findViewById(R.id.user_email);
        dob = findViewById(R.id.dob);

        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        dob.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(Registration_Activity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {

                        c.set(year, monthOfYear, dayOfMonth);
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
                        dob.setText(format.format(c.getTime()));


                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();
        });
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
    }

    public void register(@Nullable View view) {

        final String Username = Objects.requireNonNull(user_name.getText()).toString().trim();
        final String Email = Objects.requireNonNull(user_email.getText()).toString().trim();
        final String Password = Objects.requireNonNull(password.getText()).toString().trim();
        final String CPassword = Objects.requireNonNull(c_password.getText()).toString().trim();
        final String DOB = Objects.requireNonNull(dob.getText()).toString().trim();
        final String Mobile = Objects.requireNonNull(mobile_number.getText()).toString().trim();


        if (TextUtils.isEmpty(Username)) {
            user_name.setError("Please enter user name");
            user_name.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Mobile)) {
            mobile_number.setError("Please enter your mobile number");
            mobile_number.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Email)) {
            user_email.setError("Please enter your email");
            user_email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            user_email.setError("Enter a valid email");
            user_email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(DOB)) {
            dob.setError("Enter a date of birth");
            dob.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(CPassword)) {
            c_password.setError("Enter a Confirm password");
            c_password.requestFocus();
            return;
        }

        if (!Password.equals(CPassword)) {
            Toast.makeText(getApplicationContext(), "Passwords not same...", Toast.LENGTH_SHORT).show();

        } else {
            pDialog = new ProgressDialog(this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Processing...");
            pDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigUrl.RegistrationUrl,
                    response -> {
                        hidePDialog();

                        try {
                            JSONObject obj = new JSONObject(response);

                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                JSONObject data = obj.getJSONObject("data");
                                String member_id = data.getString("member_id");
                                new MaterialAlertDialogBuilder(Registration_Activity.this)
                                        .setTitle("Welcome to Epicure !!!")
                                        .setMessage(obj.getString("message"))
                                        .setPositiveButton("Ok", (dialog, which) -> {
                                            finish();
                                            startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                                            dialog.dismiss();
                                        })
                                        .show();
                                sessionManager.createUser(member_id);

                            } else {
                                hidePDialog();
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        hidePDialog();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", Username);
                    params.put("mobile", Mobile);
                    params.put("email", Email);
                    params.put("dob", DOB);
                    params.put("password", Password);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("X-Api-Key", ConfigUrl.Api_Key);
                    return headers;

                }

            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        }
        /*  startActivity(new Intent(getApplicationContext(), Login_Activity.class));
        finish();*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showNetworkMessage(isConnected);
    }


    @Override
    protected void onResume() {

        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);

    }

    public void alreadyuser(@Nullable View view) {
        startActivity(new Intent(getApplicationContext(), Login_Activity.class));
        finish();
    }
}
