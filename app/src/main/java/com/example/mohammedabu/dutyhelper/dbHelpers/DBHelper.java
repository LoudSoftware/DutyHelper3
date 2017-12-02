package com.example.mohammedabu.dutyhelper.dbHelpers;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class DBHelper {
    DatabaseReference dbref;
    Boolean saved;
    ArrayList<String> resultList = new ArrayList<>();

    public DBHelper(DatabaseReference db){
        this.dbref = db;
    }

    public Boolean save(TaskModel model){
        if(model == null){
            saved = false;
        }else{
            try {
                dbref.child("events").push().setValue(model);
                saved = true;
            } catch (DatabaseException e) {

                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }

    private void fetchData(DataSnapshot dataSnapshot){
        resultList.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            String title = ds.getValue(TaskModel.class).getTitle();
            resultList.add(title);
        }
    }

    /**
     * RETRIEVE NEWS
     */
    public ArrayList<String> retrieveNews(){
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return resultList;
    }
}