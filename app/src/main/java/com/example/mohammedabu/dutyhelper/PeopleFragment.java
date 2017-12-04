package com.example.mohammedabu.dutyhelper;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;

/**
 * Created by Mohammed on 25/09/2017.
 */

public class PeopleFragment extends Fragment {

    ImageButton settingsButton;
    PieChart pieChart;
    ImageButton profileImage;
    private FirebaseAuth mAuth;
    TextView fullName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_people2, container, false);

        mAuth = FirebaseAuth.getInstance();
        profileImage = (ImageButton) view.findViewById(R.id.profileImageButton);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsClick = new Intent(getActivity(), ProfileSettingsActivity.class);
                startActivityForResult(settingsClick, 0);
            }
        });
        settingsButton = (ImageButton) view.findViewById(R.id.settingButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsClick = new Intent(getActivity(), ProfileSettingsActivity.class);
                startActivityForResult(settingsClick, 0);
            }
        });

        fullName = (TextView) view.findViewById(R.id.userProfileFullName);


        /**
         * The code below, until line 75 is to create the Pie chart seen in activity_people2.xml
         **/
        pieChart = (PieChart) view.findViewById(R.id.profileStats);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        //pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleRadius(25f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(32f, "Completed"));
        yValues.add(new PieEntry(64f, "Overdo"));
        yValues.add(new PieEntry(21f, "Snoozed"));

        pieChart.animateY(1500, Easing.EasingOption.EaseInOutCubic);
        PieDataSet dataSet = new PieDataSet(yValues, "Stats");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);

        return view;
    }

    public void profileButton_onClick(View view) {
        Intent intent = new Intent(getContext(), ProfileSettingsActivity.class);
        startActivity(intent);
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /**
     * Updates the view according to the authentication status.
     *
     * @param user the current FirebaseUser
     */
    private void updateUI(FirebaseUser user) {
        if (user != null) {
/*            profileImage.setImageURI(null);

            Glide
                    .with(getContext())
                    .load(user.getPhotoUrl()) // the uri you got from Firebase
                    .centerCrop()
                    .into(profileImage);
            profileImage.setImageURI(user.getPhotoUrl());*/
            fullName.setText(user.getDisplayName());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage =
                (ImageView) getView().findViewById(R.id.profileImageButton);
        //Figuring out the correct image
        String drawableName = "profile1";
        switch (data.getIntExtra("imageID", R.id.teamid00)) {
            case R.id.teamid00:
                drawableName = "profile1";
                break;
            case R.id.teamid01:
                drawableName = "profile2";
                break;
            case R.id.teamid02:
                drawableName = "profile3";
                break;
            case R.id.teamid03:
                drawableName = "profile4";
                break;
            case R.id.teamid04:
                drawableName = "profile5";
                break;
            case R.id.teamid05:
                drawableName = "profile6";
                break;
            default:
                drawableName = "profile1";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable",
                getActivity().getPackageName());
        avatarImage.setImageResource(resID);
//        Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).into(profileImage); TODO fix this
    }
}


