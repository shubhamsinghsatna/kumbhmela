package com.mahakumbh.dishanirdesh.fragment.Location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mahakumbh.dishanirdesh.R;
public class MarkerDetailsBottomSheet extends BottomSheetDialogFragment {

    private String title;
    private String description;
    private String address;
    private String mobileNumber;

    // Constructor to accept dynamic data
    public MarkerDetailsBottomSheet(String title, String description, String address, String mobileNumber) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marker_details, container, false);

        // Set the data to the TextViews
        TextView titleTextView = view.findViewById(R.id.tv_title);
        TextView descriptionTextView = view.findViewById(R.id.tv_description);
//        TextView addressTextView = view.findViewById(R.id.tv_marker_address);
//        TextView mobileNumberTextView = view.findViewById(R.id.tv_marker_mobile);

        titleTextView.setText(title);
//        descriptionTextView.setText(description);
//        addressTextView.setText(address);
//        mobileNumberTextView.setText(mobileNumber);

        return view;
    }
}
