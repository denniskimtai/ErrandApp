package com.gcode.dennis.errandapp;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class Home extends Fragment {
        GridLayout gridLayout;
        Fragment fragment = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    //on click listener for each cardView element
    private void setSingleEvent(GridLayout gridLayout) {




        for (int i = 0; i<gridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);

            final int finalI = i;




            }

        }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Set title of toolbar to pick up
        getActivity().setTitle("Home");

        gridLayout  = getView().findViewById(R.id.mainGrid);
        setSingleEvent(gridLayout);

        CardView shopping = getActivity().findViewById(R.id.cardViewShopping);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //new fragment object and transaction
                Fragment fragment = new Shopping();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                //Replace fragment in framelayout with fragment you want to open
                fragmentTransaction.replace(R.id.content_frame, fragment);
                //Add transaction to backstack
                fragmentTransaction.addToBackStack(null);
                //Commit transaction
                fragmentTransaction.commit();

            }
        });

        CardView delivery = getActivity().findViewById(R.id.cardViewDelivery);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new fragment object and transaction
                Fragment fragment = new Delivery();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                //Replace fragment in framelayout with fragment you want to open
                fragmentTransaction.replace(R.id.content_frame, fragment);
                //Add transaction to backstack
                fragmentTransaction.addToBackStack(null);
                //Commit transaction
                fragmentTransaction.commit();
            }
        });

        CardView pickUp = getActivity().findViewById(R.id.cardViewPickUp);
        pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //new fragment object and transaction
                Fragment fragment = new Pickup();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                //Replace fragment in framelayout with fragment you want to open
                fragmentTransaction.replace(R.id.content_frame, fragment);
                //Add transaction to backstack
                fragmentTransaction.addToBackStack(null);
                //Commit transaction
                fragmentTransaction.commit();
            }
        });

        CardView contactUs = getActivity().findViewById(R.id.cardViewContactUs);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pop up dialog for user to choose how to contact
                contantOption();
            }

            //Method called on contact us card view is clicked
            private void contantOption() {
                CharSequence options[] = new CharSequence[] {"Call", "Sms", "Email"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                builder.setTitle("How do you wish to contact us?");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                //Place a call to the number
                                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                                phoneIntent.setData(Uri.parse("tel:+254710265413"));
                                startActivity(phoneIntent);

                                break;

                            case 1:
                                //Send an sms
                                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                                sendIntent.setData(Uri.parse("sms:" + "+254710265413"));
                                startActivity(sendIntent);
                                break;

                            case 2:
                                //Send an email
                                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                                emailIntent.setData(Uri.parse("mailto:masterkk254@gmail.com"));
                                try {
                                    startActivity(emailIntent);
                                } catch (ActivityNotFoundException e) {
                                    //When there is no email app installed
                                    Toast.makeText(getActivity(), "Failed! " + e.getMessage() + "Please try again", Toast.LENGTH_SHORT).show();
                                }

                                break;
                        }

                    }
                });

                builder.show();

            }
        });
    }

}
