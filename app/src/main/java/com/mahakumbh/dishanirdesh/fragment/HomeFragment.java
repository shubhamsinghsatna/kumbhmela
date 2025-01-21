package com.mahakumbh.dishanirdesh.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.mahakumbh.dishanirdesh.R;
import com.mahakumbh.dishanirdesh.adapter.ImageCarouselAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager1,viewPager2;
    private List<Integer> imageList,imageLeaderList;
    private Handler sliderHandler1,sliderHandler2;

//    TabLayout tabLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager1 = view.findViewById(R.id.image_carousel);
        viewPager2 = view.findViewById(R.id.image_carousel_leader);
//        tabLayout = view.findViewById(R.id.tab_layout);

        // Initialize image list
        imageList = new ArrayList<>();
        imageLeaderList = new ArrayList<>();
        imageList.add(R.drawable.slider1);
        imageList.add(R.drawable.slider2);
        imageList.add(R.drawable.slider3);

        imageLeaderList.add(R.drawable.leader1);
        imageLeaderList.add(R.drawable.leader2);
        imageLeaderList.add(R.drawable.leader3);
        imageLeaderList.add(R.drawable.leader4);
        imageLeaderList.add(R.drawable.leader5);

        // Set up ViewPager2 with adapter
        ImageCarouselAdapter adapter1 = new ImageCarouselAdapter(imageList);
        viewPager1.setAdapter(adapter1);

        ImageCarouselAdapter adapter2 = new ImageCarouselAdapter(imageLeaderList);
        viewPager2.setAdapter(adapter2);

        // Set auto-scroll
        sliderHandler1 = new Handler(Looper.getMainLooper());
        sliderHandler2 = new Handler(Looper.getMainLooper());
        viewPager1.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Reset auto-scroll on manual swipe
                sliderHandler1.removeCallbacks(sliderRunnable1);
                sliderHandler1.postDelayed(sliderRunnable1, 3000); // 3 seconds delay
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Reset auto-scroll on manual swipe
                sliderHandler2.removeCallbacks(sliderRunnable2);
                sliderHandler2.postDelayed(sliderRunnable2, 8000); // 3 seconds delay
            }
        });

        return view;
    }

    private final Runnable sliderRunnable1 = new Runnable() {
        @Override
        public void run() {
            int nextItem = (viewPager1.getCurrentItem() + 1) % imageList.size();
            viewPager1.setCurrentItem(nextItem, true);
        }
    };

    private final Runnable sliderRunnable2 = new Runnable() {
        @Override
        public void run() {
            int nextItem = (viewPager2.getCurrentItem() + 1) % imageLeaderList.size();
            viewPager2.setCurrentItem(nextItem, true);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sliderHandler1.removeCallbacks(sliderRunnable1);
        sliderHandler2.removeCallbacks(sliderRunnable2);
    }
}