package com.mahakumbh.dishanirdesh.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahakumbh.dishanirdesh.R;


public class DatesFragment extends Fragment {
    View Root;



    public DatesFragment() {
        // Required empty public constructor
    }


    public static DatesFragment newInstance(String param1, String param2) {
        DatesFragment fragment = new DatesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dates, container, false);
    }
}