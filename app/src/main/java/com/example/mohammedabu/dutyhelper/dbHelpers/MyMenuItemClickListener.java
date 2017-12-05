package com.example.mohammedabu.dutyhelper.dbHelpers;

import android.view.MenuItem;
import android.widget.PopupMenu;
import com.example.mohammedabu.dutyhelper.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

    private String uid;

    public MyMenuItemClickListener(String id) {
        this.uid = id;
    }

    private void deleteTask(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("events").child(id);
        dR.removeValue();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Modify:
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