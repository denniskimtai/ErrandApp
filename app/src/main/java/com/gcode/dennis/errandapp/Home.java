package com.gcode.dennis.errandapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class Home extends Fragment {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] slideImages = {R.drawable.pickup, R.drawable.shopping, R.drawable.delivery};
    private ArrayList<Integer> slideImagesArray = new ArrayList<Integer>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        for (int i = 0; i < slideImages.length; i++)
            slideImagesArray.add(slideImages[i]);

        mPager = rootView.findViewById(R.id.pager);
        mPager.setAdapter(new MyAdapter(getActivity(), slideImagesArray));
        CircleIndicator indicator = rootView.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        //Auto start viewPager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == slideImages.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTime = new Timer();
        swipeTime.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

        return rootView;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }

}
