package com.example.mohammedabu.dutyhelper.dbHelpers;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mohammedabu.dutyhelper.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TaskHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView description;
    private final TextView assignee;
    private final TextView dateTime;
    private final ImageView hamburgerMenuButton;

    private final ToggleButton completedToggle;

    private PopupMenu popUp;


    public TaskHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.Task_Title);
        description = (TextView) itemView.findViewById(R.id.Single_TaskDescription);
        assignee = (TextView) itemView.findViewById(R.id.Single_Task_Assignee);
        dateTime = (TextView) itemView.findViewById(R.id.Due_Date_Time);
        completedToggle = (ToggleButton)itemView.findViewById(R.id.chkState);
        hamburgerMenuButton = (ImageButton) itemView.findViewById(R.id.hamburger_card);


        //Adding a listener for the ToggleSwitch
        completedToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //checking if the toggle is on the ON state
                if (isChecked){
                    //confirmTaskCompletedDialogue();
                    showDialog("Are you sure you want to set the task to completed?!");
                }
            }
        });

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


    public ImageView getHamburgerButton() {
        return hamburgerMenuButton;
    }

    public PopupMenu getPopUp() {
        return popUp;
    }

    /**
     * A method to show the dialog when the toggle is "ON"
     * @param  message that corresponds to the title
     * @author Mohammed Abu-Zeinah
     **/

    public void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
        builder.setMessage(message).setPositiveButton("Yes, I'm sure!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                completedToggle.setChecked(true);
                //TODO: Remove the task entirely from the list and mark it as done and award the points.
            }
        }).setNegativeButton("Nope, I've changed my mind", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                completedToggle.setChecked(false);
            }
        }).show();
    }
}
