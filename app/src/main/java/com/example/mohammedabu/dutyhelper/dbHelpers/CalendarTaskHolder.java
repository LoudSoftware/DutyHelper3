package com.example.mohammedabu.dutyhelper.dbHelpers;

/**
 * Created by XavOli-Idea on 12/5/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;


import com.example.mohammedabu.dutyhelper.R;

public class CalendarTaskHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView description;
    private final TextView dateTime;


    public CalendarTaskHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.Task_Title);
        description = (TextView) itemView.findViewById(R.id.Single_TaskDescription);
        dateTime = (TextView) itemView.findViewById(R.id.Due_Date_Time);

    }

    private void showPopupMenu(View view, int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setDateTime(String dateTime) {
        this.dateTime.setText(dateTime);
    }

}
