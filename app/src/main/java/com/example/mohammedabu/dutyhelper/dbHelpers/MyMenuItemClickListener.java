package com.example.mohammedabu.dutyhelper.dbHelpers;

import android.provider.SyncStateContract;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.mohammedabu.dutyhelper.MainActivity;
import com.example.mohammedabu.dutyhelper.R;

class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

    private int position;
    public MyMenuItemClickListener(int positon) {
        this.position=positon;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.Modify:
                //TODO Add code to modify
                return true;
            case R.id.Delete:
               //TODO make some code to delete
                return true;
            default:
        }
        return false;
    }
}