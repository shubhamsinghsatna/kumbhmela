package com.mahakumbh.dishanirdesh.fragment.More;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.adapter.AnnouncementsListAdapter;
import com.mahakumbh.dishanirdesh.models.AnnouncementModel;

import java.util.ArrayList;
import java.util.List;


public class AnnouncementsFragment extends Fragment {

    private View Root;
    private final List<AnnouncementModel> announcementModelList = new ArrayList<>();

    public AnnouncementsFragment() {
        // Required empty public constructor
    }

    public static AnnouncementsFragment newInstance() {

        return new AnnouncementsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Root = inflater.inflate(R.layout.fragment_announcements, container, false);

        setUpToolBar();
        setAnnouncementList();
        initView();
        return Root;
    }

    private void initView() {
        RecyclerView rv_announcement_list = Root.findViewById(R.id.rv_announcement_list);
        rv_announcement_list.setLayoutManager(new LinearLayoutManager(requireActivity()));
        AnnouncementsListAdapter adapter = new AnnouncementsListAdapter(requireActivity(), announcementModelList);
        rv_announcement_list.setAdapter(adapter);
    }


    private void setUpToolBar() {

        ImageView back = Root.findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        TextView pageTitle = Root.findViewById(R.id.tv_page_title);
        pageTitle.setText(R.string.important_announcement_s);


    }

    private void setAnnouncementList() {

        AnnouncementModel model1 = new AnnouncementModel();
        model1.setTitle(R.string.kumbh_title);
        model1.setDetails(R.string.kumbh_details);
        model1.setBtn(R.string.kumbh_btn_title);
        model1.setLink("https://kumbh.gov.in/Content/assets/img/downloads/KumbhPamphletBothSidePrint.pdf");
        announcementModelList.add(model1);


        // Second AnnouncementModel
        AnnouncementModel model2 = new AnnouncementModel();
        model2.setTitle(R.string.vip_advisory_title);
        model2.setDetails(R.string.vip_advisory_details);
        model2.setBtn(R.string.vip_advisory_btn_title);
        model2.setLink("https://kumbh.gov.in/Content/assets/Files/Advisory/REFRAINVISITOFMAHAKUMBHAMELA.pdf");
        announcementModelList.add(model2);


        // Traffic AnnouncementModel
        AnnouncementModel model3 = new AnnouncementModel();
        model3.setTitle(R.string.traffic_title);
        model3.setDetails(R.string.traffic_details);
        model3.setBtn(R.string.traffic_btn_title);
        model3.setLink("https://kumbh.gov.in/Content/assets/Files/Advisory/TrafficPlanforMahaKumbh2025.pdf");
        announcementModelList.add(model3);

        // Quiz AnnouncementModel
        AnnouncementModel quizModel = new AnnouncementModel();
        quizModel.setTitle(R.string.quiz_title);
        quizModel.setDetails(R.string.quiz_details);
        quizModel.setBtn(R.string.quiz_btn_title);
        quizModel.setLink("https://quiz.mygov.in/quiz/mahakumbh-2025-quiz/");
        announcementModelList.add(quizModel);

        // Amusement Zones AnnouncementModel
        AnnouncementModel amusementZonesModel = new AnnouncementModel();
        amusementZonesModel.setTitle(R.string.amusement_zones_title);
        amusementZonesModel.setDetails(R.string.amusement_zones_details);
        amusementZonesModel.setBtn(0); // No button
        amusementZonesModel.setLink(null); // No link
        announcementModelList.add(amusementZonesModel);

        // Vending Zones AnnouncementModel
        AnnouncementModel vendingZonesModel = new AnnouncementModel();
        vendingZonesModel.setTitle(R.string.vending_zones_title);
        vendingZonesModel.setDetails(R.string.vending_zones_details);
        vendingZonesModel.setBtn(0); // No button
        vendingZonesModel.setLink(null); // No link
        announcementModelList.add(vendingZonesModel);
        // Agency for Activity Zones AnnouncementModel
        AnnouncementModel activityZonesModel = new AnnouncementModel();
        activityZonesModel.setTitle(R.string.activity_zones_title);
        activityZonesModel.setDetails(R.string.activity_zones_details);
        activityZonesModel.setBtn(0); // No button
        activityZonesModel.setLink(null); // No link
        announcementModelList.add(activityZonesModel);

        // Parking Facility Management AnnouncementModel
        AnnouncementModel parkingFacilityModel = new AnnouncementModel();
        parkingFacilityModel.setTitle(R.string.parking_facility_title);
        parkingFacilityModel.setDetails(R.string.parking_facility_details);
        parkingFacilityModel.setBtn(0); // No button
        parkingFacilityModel.setLink(null); // No link
        announcementModelList.add(parkingFacilityModel);

        // Advertisement Opportunities AnnouncementModel
        AnnouncementModel advertisementOpportunitiesModel = new AnnouncementModel();
        advertisementOpportunitiesModel.setTitle(R.string.advertisement_opportunities_title);
        advertisementOpportunitiesModel.setDetails(R.string.advertisement_opportunities_details);
        advertisementOpportunitiesModel.setBtn(0); // No button
        advertisementOpportunitiesModel.setLink(null); // No link
        announcementModelList.add(advertisementOpportunitiesModel);

        // Food Court AnnouncementModel
        AnnouncementModel foodCourtModel = new AnnouncementModel();
        foodCourtModel.setTitle(R.string.food_court_title);
        foodCourtModel.setDetails(R.string.food_court_details);
        foodCourtModel.setBtn(0); // No button
        foodCourtModel.setLink(null); // No link
        announcementModelList.add(foodCourtModel);

        // Milk Booths AnnouncementModel
        AnnouncementModel milkBoothsModel = new AnnouncementModel();
        milkBoothsModel.setTitle(R.string.milk_booths_title);
        milkBoothsModel.setDetails(R.string.milk_booths_details);
        milkBoothsModel.setBtn(0); // No button
        milkBoothsModel.setLink(null); // No link
        announcementModelList.add(milkBoothsModel);

        // Land and Suvidha Allotment AnnouncementModel
        AnnouncementModel landSuvidhaModel = new AnnouncementModel();
        landSuvidhaModel.setTitle(R.string.land_suvidha_title);
        landSuvidhaModel.setDetails(R.string.land_suvidha_details);
        landSuvidhaModel.setBtn(R.string.land_suvidha_btn_title); // Apply here button
        landSuvidhaModel.setLink("https://www.mklns.upsdc.gov.in/"); // Link to apply
        announcementModelList.add(landSuvidhaModel);


    }
}