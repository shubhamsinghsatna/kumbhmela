package com.mahakumbh.dishanirdesh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.activity.LocationActivity;
import com.mahakumbh.dishanirdesh.misc.LocationCategories;


public class MelaMapFragment extends Fragment {

    View Root;
    CardView cv_ghat,cv_mahatma,cv_mandir,cv_toilet,cv_food,cv_hotel,cv_bhandara,cv_parking,cv_shop,cv_prasad;
    public MelaMapFragment() {
        // Required empty public constructor
    }


    public static MelaMapFragment newInstance() {
        MelaMapFragment fragment = new MelaMapFragment();

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
        Root= inflater.inflate(R.layout.fragment_mela_map, container, false);
        initView();

        return  Root;
    }

    private void initView(){
        cv_ghat = Root.findViewById(R.id.cv_ghat);
        cv_ghat.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.GHAT)));

        cv_mahatma = Root.findViewById(R.id.cv_mahatma);
        cv_mahatma.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.MAHATMA)));

        cv_mandir = Root.findViewById(R.id.cv_mandir);
        cv_mandir.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.MANDIR)));

        cv_toilet = Root.findViewById(R.id.cv_toilet);
        cv_toilet.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.WASHROOM)));

        cv_food = Root.findViewById(R.id.cv_food);
        cv_food.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.FOOD)));

        cv_food = Root.findViewById(R.id.cv_food);
        cv_food.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.FOOD)));

        cv_hotel = Root.findViewById(R.id.cv_hotel);
        cv_hotel.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.HOTEL)));

        cv_bhandara = Root.findViewById(R.id.cv_bhandara);
        cv_bhandara.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.BHANDARA)));

        cv_parking = Root.findViewById(R.id.cv_parking);
        cv_parking.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.PARKING)));

        cv_shop = Root.findViewById(R.id.cv_shop);
        cv_shop.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.SHOP)));

        cv_prasad = Root.findViewById(R.id.cv_prasad);
        cv_prasad.setOnClickListener(v->openLocation(String.valueOf(LocationCategories.PRASAD)));
    }


    private void openLocation(String category){
        Intent intent = new Intent(requireActivity(), LocationActivity.class);
        intent.putExtra("category",category);
        requireActivity().startActivity(intent);
    }
}