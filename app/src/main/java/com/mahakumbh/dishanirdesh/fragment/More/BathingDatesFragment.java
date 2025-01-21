package com.mahakumbh.dishanirdesh.fragment.More;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahakumbh.dishanirdesh.R;


public class BathingDatesFragment extends Fragment {


    public BathingDatesFragment() {
        // Required empty public constructor
    }

    public static BathingDatesFragment newInstance() {
        BathingDatesFragment fragment = new BathingDatesFragment();

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
        return inflater.inflate(R.layout.fragment_bathing_dates, container, false);
    }
}