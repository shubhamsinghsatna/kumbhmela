package com.mahakumbh.dishanirdesh.fragment.Location;

import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.models.EntityLocationModel;


public class EntityLocationDetailsFragment extends BottomSheetDialogFragment {


    View Root;
    private EntityLocationModel currentLocation;
    ImageView iv_back,iv_image,iv_menu;
    TextView tv_title,tv_details,tv_address,tv_address_title;
    Button btn_call,btn_visit;

    public EntityLocationDetailsFragment(EntityLocationModel currentLocation) {
        // Required empty public constructor
        this.currentLocation=currentLocation;
    }


    public static EntityLocationDetailsFragment newInstance( EntityLocationModel currentLocation) {

        return new EntityLocationDetailsFragment(currentLocation);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Root= inflater.inflate(R.layout.fragment_entity_location_details, container, false);


        initView();
        setData();

        return Root;
    }

    private void initView(){
        iv_back = Root.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> requireActivity().onBackPressed());


        iv_image = Root.findViewById(R.id.iv_image);
        iv_menu = Root.findViewById(R.id.iv_menu);

        tv_title = Root.findViewById(R.id.tv_title);
        tv_details = Root.findViewById(R.id.tv_details);

        tv_address = Root.findViewById(R.id.tv_address);
        tv_address_title = Root.findViewById(R.id.tv_address_title);

        btn_call = Root.findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        btn_visit = Root.findViewById(R.id.btn_visit);
        btn_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setData(){
        tv_title.setText(currentLocation.getTitle());
        tv_details.setText(currentLocation.getDescription());

        if(currentLocation.getImage()!=0) {
            iv_image.setImageDrawable(requireContext().getDrawable(currentLocation.getImage()));
            iv_image.setVisibility(VISIBLE);
        }

        if(currentLocation.getMenu()!=0) {
            iv_menu.setImageDrawable(requireContext().getDrawable(currentLocation.getMenu()));
            iv_menu.setVisibility(VISIBLE);
        }

        if(currentLocation.getAddress()!=null) {
            tv_address.setText(currentLocation.getAddress());
            tv_address.setVisibility(VISIBLE);
            tv_address_title.setVisibility(VISIBLE);
        }

        if(currentLocation.getMobileNumber()!=null) {
            btn_call.setVisibility(VISIBLE);
        }

    }

}