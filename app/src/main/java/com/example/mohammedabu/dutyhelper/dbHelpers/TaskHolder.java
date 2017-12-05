package com.example.mohammedabu.dutyhelper.dbHelpers;


import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.mohammedabu.dutyhelper.R;

public class TaskHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView description;
    private final TextView assignee;
    private final TextView dateTime;
    private final ImageView hamburgerMenuButton;
    private final ImageView radioButton;


    public TaskHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.Task_Title);
        description = (TextView) itemView.findViewById(R.id.Single_TaskDescription);
        assignee = (TextView) itemView.findViewById(R.id.Single_Task_Assignee);
        dateTime = (TextView) itemView.findViewById(R.id.Due_Date_Time);

        hamburgerMenuButton = (ImageButton) itemView.findViewById(R.id.hamburger_card);
        hamburgerMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(hamburgerMenuButton, getAdapterPosition());
            }
        });

        radioButton = (ImageView) itemView.findViewById(R.id.iv_image);
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

    public void setStatus(boolean completed) {
        int resID;
        if (completed) {
            resID = R.drawable.abc_btn_radio_to_on_mtrl_015;
        } else {
            resID = R.drawable.abc_btn_radio_to_on_mtrl_000;
        }
        radioButton.setImageResource(resID);
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

    public ImageView getRadioButton() {
        return radioButton;
    }
}
