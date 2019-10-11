package com.ivitesse.epicure.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.annotation.NonNull;

import com.ivitesse.epicure.activities.MainActivity;

import java.util.HashMap;

public class SessionManager {
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PICID = "profile_pic";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_USERID = "userId";
    public static final String KEY_DOB = "dob";
    public static final String KEY_ANIDATE = "anniversary_date";
    public static final String KEY_ADDRESS = "address";
    private static final String KEY_MEMTYPE = "membership_type";
    public static final String KEY_UID = "uuid";
    /* public static final String KEY_PASTCOUPON = "past_coupons";
     public static final String KEY_PASTSTAY = "past_stays";
     public static final String KEY_FUTUREBOOKINGS = "future_bookings";
     public static final String KEY_FUTURECOUPONS = "future_coupons";
     public static final String KEY_PASTRR = "past_ratings_and_reviews";
     public static final String KEY_TRANSACTION = "transaction_history";
 */
    //@NonNull String userId,@NonNull String email,@NonNull String name,@NonNull String mobile,@NonNull String profile_pic,@NonNull String dob,@NonNull String anniversary_date,@NonNull String
// address,@NonNull String member_id,@NonNull String membership_type,@NonNull String past_coupons,@NonNull String future_bookings,@NonNull String future_coupons,@NonNull String past_ratings_and_reviews,@NonNull String transaction_history,@NonNull String past_stays
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String PREF_NAME = "epiapp";
    private final SharedPreferences pref;
    private final Editor editor;
    private final Context _context;


    @SuppressLint("CommitPrefEdits")
    public SessionManager(@NonNull Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }


    public void updateprofile(@NonNull String email, @NonNull String name, @NonNull String mobile,
                              @NonNull String profile_pic, @NonNull String dob, @NonNull String anniversary_date,
                              @NonNull String address) {

        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_PICID, profile_pic);
        editor.putString(KEY_DOB, dob);
        editor.putString(KEY_ANIDATE, anniversary_date);
        editor.putString(KEY_ADDRESS, address);
        editor.apply();
    }

    public void createLoginSession(@NonNull String userId, @NonNull String email,
                                   @NonNull String name, @NonNull String mobile, @NonNull String profile_pic,
                                   @NonNull String dob, @NonNull String anniversary_date,
                                   @NonNull String address, @NonNull String member_id, @NonNull String membership_type

    ) {
        editor.putString(KEY_UID, member_id);
        editor.putString(KEY_USERID, userId);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_PICID, profile_pic);
        editor.putString(KEY_DOB, dob);
        editor.putString(KEY_ANIDATE, anniversary_date);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_MEMTYPE, membership_type);

        editor.apply();
    }

    public void createUser(@NonNull String uid) {
        editor.putString(KEY_UID, uid);
        editor.apply();
    }


    @NonNull
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_UID, pref.getString(KEY_UID, null));
        return user;
    }


    @NonNull
    public HashMap<String, String> getUser() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_UID, pref.getString(KEY_UID, null));
        user.put(KEY_USERID, pref.getString(KEY_USERID, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_MOBILE, pref.getString(KEY_MOBILE, null));
        user.put(KEY_PICID, pref.getString(KEY_PICID, null));
        user.put(KEY_DOB, pref.getString(KEY_DOB, null));
        user.put(KEY_ANIDATE, pref.getString(KEY_ANIDATE, null));
        user.put(KEY_ADDRESS, pref.getString(KEY_ADDRESS, null));
        user.put(KEY_MEMTYPE, pref.getString(KEY_MEMTYPE, null));
        return user;
    }


    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

}