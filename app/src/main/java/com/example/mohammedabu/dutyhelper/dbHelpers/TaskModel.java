package com.example.mohammedabu.dutyhelper.dbHelpers;

/**
 * Created by Fiona on 2017-11-27.
 */

//maybe this should've gone somewhere else..?

public class TaskModel{
    private String uid;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventDescription;
    private String assignee;
    private int eventPoints;
    private boolean completed;

    public TaskModel() {

    } // For Firebase

    public TaskModel(String eventName, String eventDate, String eventTime, String eventDescription, int eventPoints, String assignee, String uid) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
        this.eventPoints = eventPoints;
        this.assignee = assignee;
        this.completed = false;
        this.uid = uid;
    }


    public void setEventPoints(int eventPoints) {
        this.eventPoints = eventPoints;
    }

    public void setCompleted(boolean status) {
        this.completed = status;
    }

    public void setId(String taskID) {
        this.uid = taskID;
    }

    public String getUid() {
        return uid;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setPoints(int eventPoints) {
        this.eventPoints = eventPoints;
    }

    public int getPoints() {
        return eventPoints;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getTime() {
        return eventTime;
    }

    public boolean getCompleted() {
        return completed;
    }

}