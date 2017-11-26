package com.example.mohammedabu.dutyhelper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mohammedabu.dutyhelper.dbHelpers.UserHelper;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

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
        settingsButton = (ImageButton) view.findViewById(R.id.settingButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsClick = new Intent(getActivity(), ProfileSettingsActivity.class);
                getActivity().startActivity(settingsClick);
            }
        });
        profileImage = (ImageButton) view.findViewById(R.id.imageButton4);

        fullName = (TextView) view.findViewById(R.id.userProfileFullName);
        mAuth = FirebaseAuth.getInstance();

      
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

       // byte[] byteArray = getArgument().getByteArrayExtra("image");
       // Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);



        return view;


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
     * @param user the current FirebaseUser
     */
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            fullName.setText(user.getDisplayName());
        }
    }


}


