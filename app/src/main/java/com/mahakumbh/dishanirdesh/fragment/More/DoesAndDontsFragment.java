package com.mahakumbh.dishanirdesh.fragment.More;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahakumbh.dishanirdesh.R;

public class DoesAndDontsFragment extends Fragment {
    private View Root;

    private CardView cv_do_s,cv_don_ts;
    private TextView tv_do_s,tv_don_ts,text_holder;


    public static DoesAndDontsFragment newInstance() {
        DoesAndDontsFragment fragment = new DoesAndDontsFragment();
        Bundle args = new Bundle();

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
        Root= inflater.inflate(R.layout.fragment_does_and_donts, container, false);
        setUpToolBar();
        initView();
        return Root;
    }

    private void initView(){
        tv_do_s = Root.findViewById(R.id.tv_do_s);
        tv_don_ts = Root.findViewById(R.id.tv_don_ts);
        text_holder = Root.findViewById(R.id.tv_dos_and_donts_details);

        cv_do_s = Root.findViewById(R.id.cv_do_s);
        cv_do_s.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                text_holder.setText(R.string.do_list);

                cv_do_s.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bhagwa));
                tv_do_s.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));

                cv_don_ts.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white));
                tv_don_ts.setTextColor(ContextCompat.getColor(requireContext(), R.color.bhagwa));

            }
        });

        cv_don_ts = Root.findViewById(R.id.cv_don_ts);
        cv_don_ts.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                text_holder.setText(R.string.don_ts_list);

                cv_do_s.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white));
                tv_do_s.setTextColor(ContextCompat.getColor(requireContext(), R.color.bhagwa));

                cv_don_ts.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.bhagwa));
                tv_don_ts.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
            }
        });

    }

    private void setUpToolBar(){

        ImageView back = Root.findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        TextView pageTitle = Root.findViewById(R.id.tv_page_title);
        pageTitle.setText(R.string.do_s_amp_don_ts);


    }
}