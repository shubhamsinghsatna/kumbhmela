package com.mahakumbh.dishanirdesh.fragment.More;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahakumbh.dishanirdesh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HowToReachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HowToReachFragment extends Fragment {


    public HowToReachFragment() {
        // Required empty public constructor
    }

    public static HowToReachFragment newInstance() {
        HowToReachFragment fragment = new HowToReachFragment();

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
        return inflater.inflate(R.layout.fragment_how_to_reach, container, false);
    }
}