package com.example.mohammedabu.dutyhelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.mohammedabu.dutyhelper.dbHelpers.TaskHolder;
import com.example.mohammedabu.dutyhelper.dbHelpers.TaskModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
                viewHolder.setPoints(model.getPoints());
                viewHolder.getCompletedToggle().setChecked(model.getCompleted());

                //Adding a listener for the ToggleSwitch
                viewHolder.getCompletedToggle().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //checking if the toggle is on the ON state
                        if (isChecked) {
                            //confirmTaskCompletedDialogue();
                            showDialog("Are you sure you want to set the task to completed?!", viewHolder, model);
                        } else {
                            //Update the DB
                            FirebaseDatabase.getInstance().getReference("events")
                                    .child(model.getUid())
                                    .child("completed")
                                    .setValue(false);
                            viewHolder.getCompletedToggle().setChecked(false);
                        }
                    }
                });


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

    /**
     * A method to show the dialog when the toggle is "ON"
     *
     * @param message the Dialog's title
     * @author Mohammed Abu-Zeinah
     **/
    public void showDialog(String message, final TaskHolder viewHolder, final TaskModel model) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message).setPositiveButton("Yes, I'm sure!", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Update the DB
                FirebaseDatabase.getInstance().getReference("events")
                        .child(model.getUid())
                        .child("completed")
                        .setValue(true);
//                viewHolder.getCompletedToggle().setChecked(true);
                //TODO: Remove the task entirely from the list and mark it as done and award the points.
            }
        }).setNegativeButton("Nope, I've changed my mind", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewHolder.getCompletedToggle().setChecked(false);
                    }
                }).show();
    }

    public class MyMenuItemClickListener extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {



        private String uid;

        public MyMenuItemClickListener() {
        }

        public MyMenuItemClickListener(String id) {
            this.uid = id;
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

    private void deleteTask(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("events").child(id);
        dR.removeValue();
    }



    public void modifyTask(String uid) {

        Fragment updateFragment = new UpdateActivity();
        Bundle bundle = new Bundle();
        bundle.putString("uid", uid);

        updateFragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(getId(), updateFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //CalendarEvent event=new CalendarEvent(name, date, time, description, 0, "user");


        // TaskModel event = new TaskModel(null, null, null, null, null, null);


    }
}
