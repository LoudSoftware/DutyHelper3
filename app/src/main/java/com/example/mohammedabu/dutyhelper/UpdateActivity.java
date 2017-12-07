package com.example.mohammedabu.dutyhelper;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammedabu.dutyhelper.dbHelpers.TaskModel;
import com.example.mohammedabu.dutyhelper.dbHelpers.UserHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by Erivwo on 05/12/2017.
 */

public class UpdateActivity extends Fragment {

    private static final int uniqueID = 45612;
    private final String TAG = "UpdateActivity";
    ImageButton datePicker;
    Button cancel;
    ImageButton timePicker;
    TextView taskDate;
    TextView taskTime;
    EditText task;
    EditText taskDescription;
    DatabaseReference db;
    String userID;
    FirebaseAuth firebaseAuth;
    //creating objects to create a notification to the user.
    NotificationCompat.Builder notification;
    private Spinner assigneeSpinner;
    private Button update;
    private Spinner pointsSpinner;
    private String uid;
    private ImageView backButton;
    private TaskModel model;
    private DatabaseReference dR;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.uid = getArguments().getString("uid");
        }
        dR = FirebaseDatabase.getInstance().getReference("events").child(uid); // Sets the db ref


        userID = new UserHelper(FirebaseAuth.getInstance()).getUID();

        View view = inflater.inflate(R.layout.activity_update, container, false);
        //Creating the Spinner with the user's name into the application.
        assigneeSpinner = (Spinner) view.findViewById(R.id.create_UserSelection);
        //The Strings that will use the users names from the array string in the strings.xml file in resources.
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assigneeSpinner.setAdapter(myAdapter);


        // Makes the back button act as a back button.
        backButton = (ImageView) view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToTasks();
            }
        });

        update = (Button) view.findViewById(R.id.buttonUpdate);
        taskDate = (TextView) view.findViewById(R.id.taskDate);
        taskTime = (TextView) view.findViewById(R.id.taskTime);
        task = (EditText) view.findViewById(R.id.task);
        taskDescription = (EditText) view.findViewById(R.id.taskDescription);


        //Creating the Spinner with the user's name into the application.
        pointsSpinner = (Spinner) view.findViewById(R.id.create_pointsAllocated);

        dR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                model = dataSnapshot.getValue(TaskModel.class);
                task.setText(model.getEventName());
                taskDescription.setText(model.getEventDescription());
                taskTime.setText(model.getTime());
                taskDate.setText(model.getEventDate());
                pointsSpinner.setSelection(model.getPoints() - 1);
                System.out.println(model);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        //The Strings that will use the users names from the array string in the strings.xml file in resources.
        ArrayAdapter<String> myAdapterPoint = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.points));
        myAdapterPoint.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pointsSpinner.setAdapter(myAdapterPoint);


        cancel = (Button) view.findViewById(R.id.buttonCancel);
        //navigating from this fragment page to the calendar page once the cancel button is clicked
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToTasks();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateProduct()) { // If the update succeeds
                    notification = new NotificationCompat.Builder(getContext());
                    notification.setAutoCancel(true);
                    createNotification();
                    returnToTasks();
                }
            }
        });

        timePicker = (ImageButton) view.findViewById(R.id.imageButtonTimePicker);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timeDialog = new TimeFragment();
                timeDialog.show(getFragmentManager(), "TimePicker");
            }
        });


        datePicker = (ImageButton) view.findViewById(R.id.imageButtonDatePicker);
        //popping the datePicker dialogue when select date is pressed.
        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogFragment dateDialog = new DateFragment();
                dateDialog.show(getFragmentManager(), "DatePicker");

            }
        });

        return view;
    }

    private boolean updateProduct() {
        String name = task.getText().toString().trim();
        String date = taskDate.getText().toString().trim();

        if (!(TextUtils.isEmpty(name) | TextUtils.isEmpty(date))) {
            dR.child("eventName").setValue(name);
            dR.child("eventDate").setValue(date);
            dR.child("eventDescription").setValue(taskDescription.getText().toString().trim());
            dR.child("time").setValue(taskTime.getText().toString().trim());
            dR.child("assignee").setValue(assigneeSpinner.getSelectedItem().toString().trim());
            dR.child("points").setValue(Integer.parseInt(pointsSpinner.getSelectedItem().toString()));
            dR.child("completed").setValue(false);
            return true;
        } else {
            Toast.makeText(getActivity(), "Please enter required event details", Toast.LENGTH_LONG).show();
            return false;
        }
    }


    public void createNotification() {
        //specifying the notification's attributes
        notification.setSmallIcon(R.drawable.logo5);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Task Updated");

        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(uniqueID, notification.build());
        }
    }

    private void returnToTasks() {
        Fragment fragment = new Tab1ToDoFragement();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(getId(), fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}