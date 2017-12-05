package com.example.mohammedabu.dutyhelper.dbHelpers;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mohammedabu.dutyhelper.R;

public class TaskHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView description;
    private final TextView assignee;
    private final TextView dateTime;
    private final ImageView hamburgerMenuButton;
    private final ToggleButton completedToggle;

    public TaskHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.Task_Title);
        description = (TextView) itemView.findViewById(R.id.Single_TaskDescription);
        assignee = (TextView) itemView.findViewById(R.id.Single_Task_Assignee);
        dateTime = (TextView) itemView.findViewById(R.id.Due_Date_Time);
        completedToggle = (ToggleButton)itemView.findViewById(R.id.chkState);

        hamburgerMenuButton = (ImageButton) itemView.findViewById(R.id.hamburger_card);
        hamburgerMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(hamburgerMenuButton, getAdapterPosition());
            }
        });

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

    public void setAssignee(String assignee) {
        this.assignee.setText(assignee);
    }

    public void setDateTime(String dateTime) {
        this.dateTime.setText(dateTime);
    }



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
