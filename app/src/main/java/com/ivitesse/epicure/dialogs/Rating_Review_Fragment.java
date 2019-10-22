package com.ivitesse.epicure.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.button.MaterialButton;
import com.ivitesse.epicure.R;
import com.ivitesse.epicure.activities.BaseFragments;
import com.ivitesse.epicure.activities.Review_Rating;
import com.ivitesse.epicure.helper.ConfigUrl;
import com.ivitesse.epicure.helper.SessionManager;
import com.ivitesse.epicure.volleydata.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Rating_Review_Fragment extends BaseFragments {

    private static final String TAG = "example_dialog";
    private Toolbar toolbar;
    private SessionManager session;
    private String USERID, Rating;
    private AppCompatEditText title, title_review;
    private AppCompatRatingBar rating;
    private MaterialButton button_submit;

    public static void display(@Nullable FragmentManager fragmentManager) {
        Rating_Review_Fragment exampleDialog = new Rating_Review_Fragment();
        exampleDialog.show(fragmentManager, TAG);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_new_rating, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        session = new SessionManager(getActivity());
        HashMap<String, String> users = session.getUser();
        USERID = users.get(SessionManager.KEY_USERID);
        title_review = view.findViewById(R.id.title_review);
        title = view.findViewById(R.id.title);
        rating = view.findViewById(R.id.rating);
        button_submit = view.findViewById(R.id.button_submit);
        button_submit.setOnClickListener(v -> SubmitReview());

        Rating = "3.5";
        rating.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (rating < 0.5f) {
                ratingBar.setRating(0.5f);
            } else {
                Rating = (String.valueOf(rating));
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle(getString(R.string.add_review));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        //  toolbar.inflateMenu(R.menu.example_dialog);
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });


    }

    private void SubmitReview() {
        final String review = Objects.requireNonNull(title_review.getText()).toString().trim();
        final String title_tille = Objects.requireNonNull(title.getText()).toString().trim();

        showLoading();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigUrl.addRatingAndReview,
                response -> {
                    hideLoading();
                    try {
                        JSONObject obj = new JSONObject(response);


                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), Review_Rating.class));
                            getActivity().finish();
                        } else {
                            hideLoading();
                            Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    hideLoading();
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
        ) {
            @SuppressLint("SyntheticAccessor")
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userId", USERID);
                params.put("rating", Rating);
                params.put("review", review);
                params.put("title", title_tille);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-Api-Key", ConfigUrl.Api_Key);
                return headers;

            }

        };

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideLoading();
    }


}