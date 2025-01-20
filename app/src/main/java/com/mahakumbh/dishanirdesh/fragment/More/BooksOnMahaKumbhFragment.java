package com.mahakumbh.dishanirdesh.fragment.More;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahakumbh.dishanirdesh.R;

public class BooksOnMahaKumbhFragment extends Fragment {
    private View Root;

    public BooksOnMahaKumbhFragment() {
        // Required empty public constructor
    }


    public static BooksOnMahaKumbhFragment newInstance() {

        return new BooksOnMahaKumbhFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Root= inflater.inflate(R.layout.fragment_books_on_maha_kumbh, container, false);
        setUpToolBar();
        return Root;
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
        pageTitle.setText(R.string.book_s_on_mahakumbh);


    }
}