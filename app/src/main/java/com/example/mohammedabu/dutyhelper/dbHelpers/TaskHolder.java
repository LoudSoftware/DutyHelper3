package com.example.mohammedabu.dutyhelper.dbHelpers;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mohammedabu.dutyhelper.R;

/**
 * Acts as the viewHolder for a task in the Task tab RecyclerView
 */
public class TaskHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView description;
    private final TextView assignee;
    private final TextView dateTime;
    private final ImageView hamburgerMenuButton;

    private final ToggleButton completedToggle;
    private final TextView points;


    public TaskHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.Task_Title);
        description = (TextView) itemView.findViewById(R.id.Single_TaskDescription);
        assignee = (TextView) itemView.findViewById(R.id.Single_Task_Assignee);
        dateTime = (TextView) itemView.findViewById(R.id.Due_Date_Time);
        completedToggle = (ToggleButton) itemView.findViewById(R.id.chkState);

        hamburgerMenuButton = (ImageButton) itemView.findViewById(R.id.hamburger_card);

        points = (TextView) itemView.findViewById(R.id.Task_Points);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setAssignee(String assignee) {
        this.assignee.setText(assignee);
    }

    public void setDateTime(String dateTime) {
        this.dateTime.setText(dateTime);
    }

    public void setPoints(int points) {
        String text = String.valueOf(points) + " Points";
        this.points.setText(text);
    }

    public ImageView getHamburgerButton() {
        return hamburgerMenuButton;
    }

    public ToggleButton getCompletedToggle() {
        return completedToggle;
    }
}
