package com.example.mohammedabu.dutyhelper;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mohammedabu.dutyhelper.Adapters.ViewPagerAdapter;

public class activity_tasks extends AppCompatActivity {

    private static final String TAG = "Task Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        Log.d(TAG, "onCreate: Starting.");


        // Set up the ViewPager with the sections adapter.
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        final ViewPager pager = (ViewPager) findViewById(R.id.container);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        setupViewPager(adapter, pager);
        tabs.setupWithViewPager(pager);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tabs));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(ViewPagerAdapter adapter, ViewPager viewPager) {
        adapter.addFragment(new Tab1ToDoFragement(), "To do");
        viewPager.setAdapter(adapter);
    }


}
