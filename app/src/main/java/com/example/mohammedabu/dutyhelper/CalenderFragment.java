package com.example.mohammedabu.dutyhelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.mohammedabu.dutyhelper.Authentication.Login;
import com.example.mohammedabu.dutyhelper.Authentication.RegisterActivity;

/**
 * Created by Mohammed on 25/09/2017.
 */

public class CalenderFragment extends Fragment {
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    CalendarView calendarView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calender, container, false);
        calendarView = (CalendarView)view.findViewById(R.id.calendarView2);
        button = (Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notification = new NotificationCompat.Builder(getContext());
                notification.setAutoCancel(true);
                createNotification();
                //navigating from this fragment page to the create page once the create task is clicked.
                Fragment fragment = new CreateFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(getId(), fragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        return view;
    }

    public void createNotification(){
        //specifying the notification's attributes
        notification.setSmallIcon(R.drawable.logo5);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Task Created");

//        //Specifying the intent when the notification is clicked. Will take the user to the tasks page.
//        Intent intent = new Intent(getContext(),TasksFragment.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setContentIntent(pendingIntent);

        //Building the notification and issue it.
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
        notificationManager.notify(uniqueID, notification.build());
    }

}
