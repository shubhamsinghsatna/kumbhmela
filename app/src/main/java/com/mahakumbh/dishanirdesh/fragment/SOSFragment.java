package com.mahakumbh.dishanirdesh.fragment;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.Manifest;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mahakumbh.dishanirdesh.R;

public class SOSFragment extends Fragment {

    private static final int REQUEST_CALL_PERMISSION = 1;

    LinearLayout llPoliceHelpline, llKumbhHelp;
    LinearLayout llSosAnim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_s_o_s, container, false);

        llPoliceHelpline = view.findViewById(R.id.llPoliceHelpline);
        llKumbhHelp = view.findViewById(R.id.llKumbhHelp);
        llSosAnim = view.findViewById(R.id.llSosAnim);

        // Set click listeners
        llPoliceHelpline.setOnClickListener(v -> makePhoneCall("112"));
        llKumbhHelp.setOnClickListener(v -> makePhoneCall("1920"));
        llSosAnim.setOnClickListener(v -> makePhoneCall("1920"));


        return view;
    }

    private void makePhoneCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PERMISSION_GRANTED) {
            // Intent to make a call
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        } else {
            // Request the CALL_PHONE permission
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Permission granted. Try again.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}