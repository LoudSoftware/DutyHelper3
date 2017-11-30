package com.example.mohammedabu.dutyhelper;

import android.app.TimePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Fiona on 2017-11-27.
 */

public class TimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this,  hourOfDay, minute, false);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minuteInt) {
       String am_pm="";
       int hour;
       String minute;
       if (hourOfDay<12){
           am_pm="AM";
           hour=hourOfDay;
           if (hour==0){
               hour=12;
           }
       } else{
           am_pm="PM";
           hour=hourOfDay-12;
           if (hour==0){
               hour=12;
           }
       }
       if (minuteInt<10){
           minute="0"+Integer.toString(minuteInt);
       } else {
           minute=Integer.toString(minuteInt);
       }displayTime(hour, minute, am_pm);
    }

    public void displayTime(int hour, String minute, String am_pm) {
        TextView taskTime= (TextView)getActivity(). findViewById(R.id.taskTime);
        taskTime.setText(hour+":"+minute+" "+am_pm);
    }
}