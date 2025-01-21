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
import com.mahakumbh.dishanirdesh.adapter.FAQListAdapter;
import com.mahakumbh.dishanirdesh.models.FAQModel;

import java.util.ArrayList;
import java.util.List;

public class FAQFragment extends Fragment {
    View Root;

    RecyclerView rv_faq;
    private final List<FAQModel> faqModelList = new ArrayList<>();

    public FAQFragment() {
        // Required empty public constructor
    }


    public static FAQFragment newInstance() {
        FAQFragment fragment = new FAQFragment();

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
        Root = inflater.inflate(R.layout.fragment_f_a_q, container, false);

        setUpToolBar();
        setAnnouncementList();
        initView();
        return Root;
    }

    private void initView() {
        rv_faq = Root.findViewById(R.id.rv_faq);
        rv_faq.setLayoutManager(new LinearLayoutManager(requireActivity()));
        FAQListAdapter adapter = new FAQListAdapter(requireActivity(),this,faqModelList);
        rv_faq.setAdapter(adapter);
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
        pageTitle.setText(R.string.faq_s);


    }

    private void setAnnouncementList() {
        faqModelList.clear();
        FAQModel model1= new FAQModel();
        model1.setTitle(R.string.dos_and_donts_title);
        model1.setDetails(R.string.dos_and_donts_details);
        model1.setBtn(R.string.check);
        model1.setFragment(DoesAndDontsFragment.newInstance());

        faqModelList.add(model1);



        FAQModel model2= new FAQModel();
        model2.setTitle(R.string.famous_places_title);
        model2.setDetails(R.string.famous_places_details);
        model2.setBtn(R.string.check);
        model2.setFragment(FamousPlacesFragment.newInstance());

        faqModelList.add(model2);



        FAQModel model3= new FAQModel();
        model3.setTitle(R.string.how_to_reach_title);
        model3.setDetails(R.string.how_to_reach_details);
        model3.setBtn(R.string.check);
        model3.setFragment(HowToReachFragment.newInstance());

        faqModelList.add(model3);



        FAQModel model4= new FAQModel();
        model4.setTitle(R.string.bathing_dates_title);
        model4.setDetails(R.string.bathing_dates_details);
        model4.setBtn(R.string.check);
        model4.setFragment(BathingDatesFragment.newInstance());

        faqModelList.add(model4);



    }


    public void openFragment(Fragment fragment){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(fragment.getTag())
                .commit();
    }
}