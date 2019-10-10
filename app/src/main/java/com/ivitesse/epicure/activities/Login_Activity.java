package com.ivitesse.epicure.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.helper.ConfigUrl;
import com.ivitesse.epicure.helper.SessionManager;
import com.ivitesse.epicure.volleydata.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Login_Activity extends AppCompatActivity {
    private View bootomNavigation;
    private BottomSheetDialog bottomSheetDialog;
    private SessionManager sessionManager;
    AppCompatEditText mem_id, password, user_email;
    String Member_ID;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(getApplicationContext());
        mem_id = findViewById(R.id.mem_id);
        password = findViewById(R.id.password);
        HashMap<String, String> users = sessionManager.getUserDetails();
        //  imageView.setImageURI(Uri.parse(users.get(SessionManager.KEY_PICID)));
        Member_ID = users.get(SessionManager.KEY_UID);

        if (Member_ID == null) {
            mem_id.setText("");
        } else {
            mem_id.setText(Member_ID);

        }
    }

    public void login(@Nullable View view) {
        final String Member_id = Objects.requireNonNull(mem_id.getText()).toString().trim();
        final String Password = Objects.requireNonNull(password.getText()).toString().trim();

        if (TextUtils.isEmpty(Member_id)) {
            mem_id.setError("Please enter your membership id");
            mem_id.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            password.setError("Enter a password");
            password.requestFocus();
        } else {
            pDialog = new ProgressDialog(this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Checking...");
            pDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigUrl.LoginscreenUrl,
                    response -> {
                        hidePDialog();
                        try {
                            JSONObject obj = new JSONObject(response);


                            if (!obj.getBoolean("error")) {
                                JSONObject data = obj.getJSONObject("data");
                                String userId = data.getString("userId");
                                String email = data.getString("email");
                                String member_id = data.getString("member_id");
                                String name = data.getString("name");
                                String mobile = data.getString("mobile");
                                String profile_pic = data.getString("profile_pic");
                                String dob = data.getString("dob");
                                String anniversary_date = data.getString("anniversary_date");
                                String address = data.getString("address");
                                String membership_type = data.getString("membership_type");
                                String past_coupons = data.getString("past_coupons");
                                String future_bookings = data.getString("future_bookings");
                                String future_coupons = data.getString("future_coupons");
                                String past_stays = data.getString("past_stays");
                                String past_ratings_and_reviews = data.getString("past_ratings_and_reviews");
                                String transaction_history = data.getString("transaction_history");
                                sessionManager.createLoginSession(userId, email, name, mobile, profile_pic, dob, anniversary_date,
                                        address, member_id, membership_type, past_coupons, future_bookings, future_coupons, past_ratings_and_reviews, transaction_history, past_stays);

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
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
                    params.put("member_id", Member_id);
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


        /*   startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();*/


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
        user_email = bootomNavigation.findViewById(R.id.user_email);

    }

    public void forgotaccess(@NonNull View view) {
        final String Email = Objects.requireNonNull(user_email.getText()).toString().trim();

        if (TextUtils.isEmpty(Email)) {
            user_email.setError("Please enter your email");
            user_email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            user_email.setError("Enter a valid email");
            user_email.requestFocus();
            return;
        } else {
            pDialog = new ProgressDialog(this);
            // Showing progress dialog before making http request
            pDialog.setMessage("Checking Email..");
            pDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigUrl.forgetPassword,
                    response -> {
                        hidePDialog();

                        try {
                            JSONObject obj = new JSONObject(response);

                            if (!obj.getBoolean("error")) {
                                new MaterialAlertDialogBuilder(Login_Activity.this)
                                        .setTitle("Sucess !!!")
                                        .setMessage(obj.getString("message"))
                                        .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss())
                                        .show();
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
                    params.put("email", Email);
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

        bottomSheetDialog.dismiss();
    }
}
