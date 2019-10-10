package com.ivitesse.epicure.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.helper.CircularImageView;
import com.ivitesse.epicure.helper.ConfigUrl;
import com.ivitesse.epicure.helper.ConnectivityChangeReceiver;
import com.ivitesse.epicure.helper.MyApplication;
import com.ivitesse.epicure.helper.SessionManager;
import com.ivitesse.epicure.volleydata.VolleyMultipartRequest;
import com.ivitesse.epicure.volleydata.VolleySingleton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Profile_Activity extends BaseActivity implements View.OnClickListener, ConnectivityChangeReceiver.ConnectivityReceiverListener {
    private final int GALLERY = 1;
    private final int CAMERA = 2;
    private AppCompatImageView edit_profile;
    private CircularImageView profile_pic;
    private AppCompatTextView user_name;
    private AppCompatTextView user_email;
    private AppCompatTextView mobile_number;
    private AppCompatTextView dob;
    private AppCompatTextView anniversary;
    private AppCompatTextView password_change;
    private AppCompatTextView user_address;
    private MaterialButton logout;
    private Calendar c;
    private int mYear;
    private int mMonth;
    private int mDay;
    private SessionManager sessionManager;
    private String userId;
    private String email;
    private String name;
    private String mobile;
    private String profile_pic_url;
    private String dob_data;
    private String anniversary_date;
    private String address;
    private AppCompatEditText password;
    private AppCompatEditText cpassword;
    private BottomSheetDialog bottomSheetDialog;
    private TextInputLayout textInputLayout;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkit();
        setContentView(R.layout.activity_profile);
        sessionManager = new SessionManager(getApplicationContext());
        getDetails();
        init();
    }


    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("User Profile");

        profile_pic = findViewById(R.id.profile_pic);
        edit_profile = findViewById(R.id.edit_profile);
        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        mobile_number = findViewById(R.id.mobile_number);
        dob = findViewById(R.id.dob);
        anniversary = findViewById(R.id.anniversary);
        password_change = findViewById(R.id.password_change);
        user_address = findViewById(R.id.user_address);
        logout = findViewById(R.id.logout);


        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Listenersclick();//onclickListeners


    }

    @SuppressLint("SetTextI18n")
    private void Listenersclick() {
        edit_profile.setOnClickListener(this);
        profile_pic.setOnClickListener(this);
        user_name.setOnClickListener(this);
        user_email.setOnClickListener(this);
        mobile_number.setOnClickListener(this);
        dob.setOnClickListener(this);
        anniversary.setOnClickListener(this);
        password_change.setOnClickListener(this);
        user_address.setOnClickListener(this);

        logout.setOnClickListener(this);

        user_email.setText(email);
        user_name.setText(name);
        anniversary.setText(anniversary_date);

        if (address == null || address.equals("")) {
            user_address.setText("Add your Address");
        } else {
            user_address.setText(address);
        }

        dob.setText(dob_data);
        mobile_number.setText(mobile);

        Picasso.get()
                .load(profile_pic_url)
                .fit().centerCrop()
                .into(profile_pic);

    }

    private void getDetails() {
        HashMap<String, String> users = sessionManager.getUser();
        userId = users.get(SessionManager.KEY_USERID);
        email = users.get(SessionManager.KEY_EMAIL);
        name = users.get(SessionManager.KEY_NAME);
        mobile = users.get(SessionManager.KEY_MOBILE);
        dob_data = users.get(SessionManager.KEY_DOB);
        anniversary_date = users.get(SessionManager.KEY_ANIDATE);
        address = users.get(SessionManager.KEY_ADDRESS);
        profile_pic_url = users.get(SessionManager.KEY_PICID);

    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                (dialog, which) -> {
                    switch (which) {
                        case 0:
                            choosePhotoFromGallary();
                            break;
                        case 1:
                            takePhotoFromCamera();
                            break;

                    }
                });
        pictureDialog.show();
    }

    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    uploadImage(bitmap);
                    profile_pic.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(Profile_Activity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            if (data != null) {
                Bitmap thumbnail = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                profile_pic.setImageBitmap(thumbnail);
                uploadImage(thumbnail);
            }

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

    private void GetUpdations(String param, String value) {
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Processing...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigUrl.updateUserDetails,
                response -> {
                    hidePDialog();

                    try {
                        JSONObject obj = new JSONObject(response);
                        if (!obj.getBoolean("error")) {
                            JSONObject data = obj.getJSONObject("data");
                            String email = data.getString("email");
                            String name = data.getString("name");
                            String mobile = data.getString("mobile");
                            String profile_pic = data.getString("profile_pic");
                            String dob = data.getString("dob");
                            String anniversary_date = data.getString("anniversary_date");
                            String address = data.getString("address");
                            sessionManager.updateprofile(email, name, mobile, profile_pic, dob, anniversary_date, address);
                            getDetails();
                            Creation();
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
                params.put(param, value);
                params.put("userId", userId);
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


    private void uploadImage(Bitmap bitmap) {
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Processing...");
        pDialog.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, ConfigUrl.updateUserDetails,
                response -> {
                    hidePDialog();
                    try {
                        JSONObject obj = new JSONObject(new String(response.data));

                        if (!obj.getBoolean("error")) {
                            JSONObject data = obj.getJSONObject("data");
                            String email = data.getString("email");
                            String name = data.getString("name");
                            String mobile = data.getString("mobile");
                            String profile_pic = data.getString("profile_pic");
                            String dob = data.getString("dob");
                            String anniversary_date = data.getString("anniversary_date");
                            String address = data.getString("address");
                            sessionManager.updateprofile(email, name, mobile, profile_pic, dob, anniversary_date, address);
                            getDetails();
                            Creation();
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
                params.put("userId", userId);
                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("profile_pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-Api-Key", ConfigUrl.Api_Key);
                return headers;
            }
        };
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    private void Creation() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    private byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            showPictureDialog();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(error -> Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show())
                .onSameThread()
                .check();
    }

    private void openSettingsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Activity.this);
        builder.setTitle("Required Permissions");
        builder.setMessage("This app require permission to use awesome feature. Grant them in app settings.");
        builder.setPositiveButton("Take Me To SETTINGS", (dialog, which) -> {
            dialog.cancel();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 101);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();

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


    @SuppressLint("InflateParams")
    @Override
    public void onClick(@NonNull View v) {
        switch (v.getId()) {
            case R.id.profile_pic:
            case R.id.edit_profile:
                requestMultiplePermissions();
                break;

            case R.id.user_name:
                View bootomNavigation = getLayoutInflater().inflate(R.layout.edit_profile_dialog, null);
                textInputLayout = bootomNavigation.findViewById(R.id.text);

                MaterialButton button_submit = bootomNavigation.findViewById(R.id.button_submit);
                textInputLayout.setHint("Change your Name");
                textInputLayout.setCounterMaxLength(25);
                TextInputEditText all_edit = bootomNavigation.findViewById(R.id.all_edit);
                all_edit.setInputType(InputType.TYPE_CLASS_TEXT);
                button_submit.setOnClickListener(v1 -> {

                    if (Objects.requireNonNull(all_edit.getText()).toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please enter the Name.", Toast.LENGTH_SHORT).show();
                    } else {
                        final String value = all_edit.getText().toString().trim();
                        GetUpdations("name", value);
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog = new BottomSheetDialog(Profile_Activity.this);
                bottomSheetDialog.setContentView(bootomNavigation);
                bottomSheetDialog.show();
                break;

            case R.id.user_email:
                bootomNavigation = getLayoutInflater().inflate(R.layout.edit_profile_dialog, null);
                textInputLayout = bootomNavigation.findViewById(R.id.text);
                button_submit = bootomNavigation.findViewById(R.id.button_submit);
                textInputLayout.setHint("Change your Email");
                textInputLayout.setCounterMaxLength(50);

                all_edit = bootomNavigation.findViewById(R.id.all_edit);
                all_edit.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

                button_submit.setOnClickListener(v1 -> {

                    if (Objects.requireNonNull(all_edit.getText()).toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please enter the Email.", Toast.LENGTH_SHORT).show();
                    } else if (!isValidEmail(all_edit.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Please enter the valid Email.", Toast.LENGTH_SHORT).show();

                    } else {
                        final String value = all_edit.getText().toString().trim();
                        GetUpdations("email", value);
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog = new BottomSheetDialog(Profile_Activity.this);
                bottomSheetDialog.setContentView(bootomNavigation);
                bottomSheetDialog.show();
                break;
            case R.id.mobile_number:
                bootomNavigation = getLayoutInflater().inflate(R.layout.edit_profile_dialog, null);
                textInputLayout = bootomNavigation.findViewById(R.id.text);
                button_submit = bootomNavigation.findViewById(R.id.button_submit);
                textInputLayout.setHint("Change your Mobile");
                textInputLayout.setCounterMaxLength(10);
                all_edit = bootomNavigation.findViewById(R.id.all_edit);
                all_edit.setInputType(InputType.TYPE_CLASS_NUMBER);

                button_submit.setOnClickListener(v1 -> {

                    if (Objects.requireNonNull(all_edit.getText()).toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please enter the mobile no.", Toast.LENGTH_SHORT).show();
                    } else if (all_edit.getText().length() < 10) {
                        Toast.makeText(getApplicationContext(), "Please enter correct mobile no.", Toast.LENGTH_SHORT).show();
                    } else {

                        final String value = all_edit.getText().toString().trim();

                        GetUpdations("mobile", value);
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog = new BottomSheetDialog(Profile_Activity.this);
                bottomSheetDialog.setContentView(bootomNavigation);
                bottomSheetDialog.show();
                break;


            case R.id.dob:  // Get Current Date

                DatePickerDialog datePickerDialog = new DatePickerDialog(Profile_Activity.this,
                        (view, year, monthOfYear, dayOfMonth) -> {

                            c.set(year, monthOfYear, dayOfMonth);
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
                            dob.setText(format.format(c.getTime()));
                            GetUpdations("dob", format.format(c.getTime()));

                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                datePickerDialog.show();
                break;


            case R.id.anniversary:
                datePickerDialog = new DatePickerDialog(Profile_Activity.this,
                        (view, year, monthOfYear, dayOfMonth) -> {

                            c.set(year, monthOfYear, dayOfMonth);
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
                            anniversary.setText(format.format(c.getTime()));
                            GetUpdations("anniversary_date", format.format(c.getTime()));


                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                datePickerDialog.show();
                break;


            case R.id.password_change:
                bootomNavigation = getLayoutInflater().inflate(R.layout.edit_password_dialog, null);
                bottomSheetDialog = new BottomSheetDialog(Profile_Activity.this);
                bottomSheetDialog.setContentView(bootomNavigation);
                bottomSheetDialog.show();
                password = bootomNavigation.findViewById(R.id.password);
                cpassword = bootomNavigation.findViewById(R.id.c_password);
                button_submit = bootomNavigation.findViewById(R.id.button_submit);
                button_submit.setOnClickListener(v1 ->
                        {
                            final String Password = Objects.requireNonNull(password.getText()).toString().trim();
                            final String CPassword = Objects.requireNonNull(cpassword.getText()).toString().trim();
                            if (TextUtils.isEmpty(Password)) {
                                password.setError("Enter a password");
                                password.requestFocus();
                                return;
                            }
                            if (TextUtils.isEmpty(CPassword)) {
                                cpassword.setError("Enter a Confirm password");
                                cpassword.requestFocus();
                                return;
                            }

                            if (!Password.equals(CPassword)) {
                                Toast.makeText(getApplicationContext(), "Passwords not same...", Toast.LENGTH_SHORT).show();

                            } else {
                                GetUpdations("password", Password);
                            }


                        }


                );
                break;


            case R.id.user_address:
                bootomNavigation = getLayoutInflater().inflate(R.layout.edit_profile_dialog, null);
                textInputLayout = bootomNavigation.findViewById(R.id.text);

                button_submit = bootomNavigation.findViewById(R.id.button_submit);
                textInputLayout.setHint("Change your Address");
                textInputLayout.setCounterMaxLength(250);
                all_edit = bootomNavigation.findViewById(R.id.all_edit);
                all_edit.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
                button_submit.setOnClickListener(v1 -> {

                    if (Objects.requireNonNull(all_edit.getText()).toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Please enter the Address.", Toast.LENGTH_SHORT).show();
                    } else {
                        final String value = all_edit.getText().toString().trim();
                        GetUpdations("address", value);
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog = new BottomSheetDialog(Profile_Activity.this);
                bottomSheetDialog.setContentView(bootomNavigation);
                bottomSheetDialog.show();
                break;


        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();


    }
}
