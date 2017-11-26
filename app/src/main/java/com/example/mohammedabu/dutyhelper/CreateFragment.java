package com.example.mohammedabu.dutyhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Mohammed on 25/09/2017.
 */

public class CreateFragment extends Fragment {
    ImageButton datePicker;
    Button cancel;
    ImageButton today;
    ImageButton nextWeek;
    ImageButton tomorrow;
    TextView date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_create, container, false);
        //Creating the Spinner with the user's name into the application.
        Spinner mySpinner = (Spinner)view.findViewById(R.id.create_UserSelection);
        //The Strings that will use the users names from the array string in the strings.xml file in resources.
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        date = (TextView)view.findViewById(R.id.dob) ;

        cancel = (Button)view.findViewById(R.id.buttonCancel);
        //navigating from this fragment page to the calendar page once the cancel button is clicked
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new CalenderFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(getId(), fragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        today = (ImageButton)view.findViewById(R.id.imageButtonToday);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int actualMonth = month +1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                date.setText(day+"/"+actualMonth+"/"+year);
            }
        });
        nextWeek = (ImageButton)view.findViewById(R.id.imageButtonNextWeek);

        tomorrow = (ImageButton)view.findViewById(R.id.imageButtonTomorrow);
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int actualMonth = month +1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int tomorrow = day + 1;
                date.setText(tomorrow+"/"+actualMonth+"/"+year);
            }
        });

        datePicker = (ImageButton)view.findViewById(R.id.imageButtonDatePicker);
        //popping the datePicker dialogue when select date is pressed.
        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");

            }
        });

        return view;
    }

}
