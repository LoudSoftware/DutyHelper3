package com.example.mohammedabu.dutyhelper;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.graphics.Color;
import android.widget.TextView;

import com.example.mohammedabu.dutyhelper.dbHelpers.CalendarTaskHolder;
import com.example.mohammedabu.dutyhelper.dbHelpers.CalendarTaskModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.mohammedabu.dutyhelper.Authentication.Login;
import com.example.mohammedabu.dutyhelper.Authentication.RegisterActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Mohammed on 25/09/2017.
 */

public class CalenderFragment extends Fragment {
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    private Calendar currentCalender = Calendar.getInstance();
    CompactCalendarView calendarView;

    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private Button button;
    private FirebaseDatabase database;
    private DatabaseReference reference;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calender, container, false);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("events");

        calendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(false);
        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);

        //set initial title
        final TextView month = (TextView)view.findViewById(R.id.month);
        month.setText(dateFormatForMonth.format(currentCalender.getTime()));

        Event ev1 = new Event(Color.RED, 1512589754000L, "Teachers' Professional Day");
        calendarView.addEvent(ev1);


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                System.out.println("Hey");
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.Calendar_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(false);


        FirebaseRecyclerAdapter<CalendarTaskModel, CalendarTaskHolder> recycleAdapter = new FirebaseRecyclerAdapter<CalendarTaskModel, CalendarTaskHolder>(
                CalendarTaskModel.class,
                R.layout.single_task_list_2,
                CalendarTaskHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(final CalendarTaskHolder viewHolder, final CalendarTaskModel model, int position) {
                viewHolder.setTitle(model.getEventName());
                viewHolder.setDescription(model.getEventDescription());
                viewHolder.setDateTime(model.getEventDate() + " " + model.getTime());
                System.out.println(model.getEventDate());
            }
        };

        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.notifyDataSetChanged();





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
        if (notificationManager != null) {
            notificationManager.notify(uniqueID, notification.build());
        }
    }

}
