package com.ivitesse.epicure.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.helper.SessionManager;

import java.util.HashMap;
import java.util.Objects;

public class Rating_Review_Fragment extends DialogFragment {

    private static final String TAG = "example_dialog";
    private Toolbar toolbar;
    private SessionManager session;
    private AppCompatTextView copycode, ids;

    public static void display(FragmentManager fragmentManager) {
        Rating_Review_Fragment exampleDialog = new Rating_Review_Fragment();
        exampleDialog.show(fragmentManager, TAG);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_new_rating, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        session = new SessionManager(getActivity());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
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
}