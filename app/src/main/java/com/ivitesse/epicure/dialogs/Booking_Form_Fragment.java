package com.ivitesse.epicure.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.ivitesse.epicure.R;
import com.ivitesse.epicure.helper.SessionManager;

import java.util.Objects;

public class Booking_Form_Fragment extends DialogFragment {

    private static final String TAG = "example_dialog";
    private Toolbar toolbar;
    private SessionManager session;
    private AppCompatButton book_hotel;

    public static void display(@Nullable FragmentManager fragmentManager) {
        Booking_Form_Fragment exampleDialog = new Booking_Form_Fragment();
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
        View view = inflater.inflate(R.layout.activity_hotel_booking, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        session = new SessionManager(getActivity());

        book_hotel = view.findViewById(R.id.book_hotel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle(getString(R.string.book_now));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        book_hotel.setOnClickListener(v -> Toast.makeText(getActivity(), "Booking Sucessfully Done", Toast.LENGTH_LONG).show()
        );
        //  toolbar.inflateMenu(R.menu.example_dialog);
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });


    }
}