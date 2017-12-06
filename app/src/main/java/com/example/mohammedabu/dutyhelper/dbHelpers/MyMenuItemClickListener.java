package com.example.mohammedabu.dutyhelper.dbHelpers;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.PopupMenu;
import com.example.mohammedabu.dutyhelper.R;
import com.example.mohammedabu.dutyhelper.UpdateActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MyMenuItemClickListener extends Activity implements PopupMenu.OnMenuItemClickListener {

    private String uid;

    public MyMenuItemClickListener(String id) {
        this.uid = id;
    }

    private void deleteTask(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("events").child(id);
        dR.removeValue();
    }

    private void modifyTask(String uid){
        DatabaseReference dR  = FirebaseDatabase.getInstance().getReference("events").child(uid);
        Intent myIntent = new Intent(this, UpdateActivity.class);
        myIntent.putExtra("uid", uid);
        this.startActivity(myIntent);

        //CalendarEvent event=new CalendarEvent(name, date, time, description, 0, "user");



       // TaskModel event = new TaskModel(null, null, null, null, null, null);


    }





    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Modify:
                modifyTask(uid);
                //TODO get code from fiona
                return true;
            case R.id.Delete:
                deleteTask(uid);
                //TODO make some code to delete
                return true;
            default:
        }
        return false;
    }
}