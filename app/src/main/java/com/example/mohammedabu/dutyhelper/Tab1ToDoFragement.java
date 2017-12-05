package com.example.mohammedabu.dutyhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.mohammedabu.dutyhelper.dbHelpers.MyMenuItemClickListener;
import com.example.mohammedabu.dutyhelper.dbHelpers.TaskHolder;
import com.example.mohammedabu.dutyhelper.dbHelpers.TaskModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tab1ToDoFragement extends Fragment {
    private static final String TAG = "Tab1ToDoFragement";

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ImageView radioButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_todo, container, false);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("events");


        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.TODO_ListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(false);


        FirebaseRecyclerAdapter<TaskModel, TaskHolder> recycleAdapter = new FirebaseRecyclerAdapter<TaskModel, TaskHolder>(
                TaskModel.class,
                R.layout.single_task_list,
                TaskHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(final TaskHolder viewHolder, final TaskModel model, int position) {
                viewHolder.setTitle(model.getEventName());
                viewHolder.setDescription(model.getEventDescription());
                viewHolder.setAssignee(model.getAssignee());
                viewHolder.setDateTime(model.getEventDate() + " " + model.getTime());
                viewHolder.setStatus(model.getCompleted());

                //Sets the
                viewHolder.getRadioButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model.setCompleted(!model.getCompleted());
                        notifyDataSetChanged();
                        viewHolder.setStatus(model.getCompleted());
                    }
                }); //TODO make this update server side

                viewHolder.getHamburgerButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupMenu(viewHolder.getHamburgerButton(), model);
                    }
                });

            }
        };


        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.notifyDataSetChanged();

        return view;
    }

    private void showPopupMenu(View view, TaskModel model) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(model.getUid()));
        popup.show();
    }
}
