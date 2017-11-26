package com.example.mohammedabu.dutyhelper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Mohammed on 26/11/2017.
 */

public class DateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        populateSetDate(year, month+1, day);
    }
    public void populateSetDate(int year, int month, int day) {
        TextView dob= (TextView)getActivity(). findViewById(R.id.dob);
        dob.setText(day+"/"+month+"/"+year);
    }
}
