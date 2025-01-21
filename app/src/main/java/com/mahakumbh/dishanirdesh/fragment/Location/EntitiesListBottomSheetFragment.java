package com.mahakumbh.dishanirdesh.fragment.Location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.adapter.EntitiesLocationListAdapter;
import com.mahakumbh.dishanirdesh.models.EntityLocationModel;

import java.util.List;


public class EntitiesListBottomSheetFragment extends BottomSheetDialogFragment {

    View Root;
    ImageView close;
    TextView title;
    RecyclerView rv_locationList;
    List<EntityLocationModel> modelList;


    public EntitiesListBottomSheetFragment(List<EntityLocationModel> modelList) {
        // Required empty public constructor
        this.modelList = modelList;
    }

    public static EntitiesListBottomSheetFragment newInstance(List<EntityLocationModel> modelList) {

        return new EntitiesListBottomSheetFragment(modelList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Root = inflater.inflate(R.layout.fragment_entities_list_bottom_sheet, container, false);


        initView();

        return Root;
    }

    private void initView() {
        close = Root.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        title = Root.findViewById(R.id.title);


        rv_locationList = Root.findViewById(R.id.rv_locationList);
        rv_locationList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        EntitiesLocationListAdapter adapter = new EntitiesLocationListAdapter(requireActivity(), modelList);
        rv_locationList.setAdapter(adapter);
    }
}