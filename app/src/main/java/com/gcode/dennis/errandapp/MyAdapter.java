package com.gcode.dennis.errandapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

//Adapter to populate cstom content in viewPager
public class MyAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    //Public constructor
    public MyAdapter (Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }

    //return number of available views in viewpager
    @Override
    public int getCount() {
        return images.size();

    }

    //Create page for position passed as argument
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //Inflate slide.xml layout
        View myImageLayout = inflater.inflate(R.layout.slide, container, false);
        ImageView myImage = myImageLayout.findViewById(R.id.slideImage);
        myImage.setImageResource(images.get(position));
        //Inflated view added to viewpager
        container.addView(myImageLayout, 0);
        //return inflated view
        return myImageLayout;

    }

    //Check if the view passed to it is associated with the key returned by instantiateItem()
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }
}
