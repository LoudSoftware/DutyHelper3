package com.example.mohammedabu.dutyhelper;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.TextView;

import com.example.mohammedabu.dutyhelper.dbHelpers.CalendarTaskHolder;
import com.example.mohammedabu.dutyhelper.dbHelpers.TaskModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Mohammed on 25/09/2017.
 */

public class CalenderFragment extends Fragment {
    private static final int uniqueID = 45612;
    NotificationCompat.Builder notification;
    CompactCalendarView calendarView;
    private Calendar currentCalender = Calendar.getInstance();
    private SimpleDateFormat dateForCalendar = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    private SimpleDateFormat pullForCalendar = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private Button button;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Query query;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calender, container, false);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("events");
        calendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        calendarView.setUseThreeLetterAbbreviation(false);
        calendarView.setFirstDayOfWeek(Calendar.SUNDAY);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.Calendar_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(false);

        //set initial title
        final TextView month = (TextView) view.findViewById(R.id.month);
        month.setText(dateFormatForMonth.format(currentCalender.getTime()));

        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Date date = new Date(dateClicked.getTime());
                pullForCalendar.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
                String formatted = pullForCalendar.format(date);
                System.out.println(formatted);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        FirebaseRecyclerAdapter<TaskModel, CalendarTaskHolder> recycleAdapter = new FirebaseRecyclerAdapter<TaskModel, CalendarTaskHolder>(
                TaskModel.class,
                R.layout.single_task_list_2,
                CalendarTaskHolder.class,
                reference
        ) {
            @Override
            protected void populateViewHolder(final CalendarTaskHolder viewHolder, final TaskModel model, int position) {
                viewHolder.setTitle(model.getEventName());
                viewHolder.setDescription(model.getEventDescription());
                viewHolder.setDateTime(model.getEventDate());
                String thisDate = model.getEventDate() + " " + model.getTime();
                try {
                    Date date = dateForCalendar.parse(thisDate);
                    long epoch = date.getTime();
                    Event ev1 = new Event(Color.RED, epoch, model.getEventName());
                    calendarView.addEvent(ev1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.notifyDataSetChanged();


        button = (Button) view.findViewById(R.id.button);
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


    public void createNotification() {
        //specifying the notification's attributes
        notification.setSmallIcon(R.drawable.logo5);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Task Created");

        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(uniqueID, notification.build());
        }
    }

}
