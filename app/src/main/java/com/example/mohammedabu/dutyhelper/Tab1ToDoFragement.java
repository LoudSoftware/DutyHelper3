package com.example.mohammedabu.dutyhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammedabu.dutyhelper.dbHelpers.TaskHolder;
import com.example.mohammedabu.dutyhelper.dbHelpers.TaskModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tab1ToDoFragement extends Fragment {
    private static final String TAG = "Tab1ToDoFragement";

    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_todo, container, false);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("events");


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.TODO_ListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerAdapter<TaskModel, TaskHolder> recycleAdapter = new FirebaseRecyclerAdapter<TaskModel, TaskHolder>(
                TaskModel.class,
                R.layout.single_task_list,
                TaskHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(TaskHolder viewHolder, TaskModel model, int position) {
                viewHolder.setTitle(model.getEventName());
                viewHolder.setDescription(model.getEventDescription());
                viewHolder.setAssignee(model.getAssignee());
                viewHolder.setDateTime(model.getEventDate() + " " + model.getTime());
            }
        };

        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.notifyDataSetChanged();

        return view;
    }
}
