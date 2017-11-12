package com.example.mohammedabu.dutyhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mohammedabu.dutyhelper.R;

public class Tab1ToDoFragement extends Fragment {
    private static final String TAG = "Tab1ToDoFragement";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_todo,container,false);

        return view;
    }
}
