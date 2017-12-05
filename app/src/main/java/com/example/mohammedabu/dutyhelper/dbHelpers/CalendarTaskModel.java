package com.example.mohammedabu.dutyhelper.dbHelpers;

/**
 * Created by XavOli-Idea on 12/5/2017.
 */

public class CalendarTaskModel {
    private String taskID;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventDescription;

    public CalendarTaskModel() {

    } // For Firebase

    public CalendarTaskModel(String eventName, String eventDate, String eventTime, String eventDescription) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
    }


    public void setId(String taskID) {
        this.taskID = taskID;
    }

    public String getId() {
        return taskID;
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

    public void setTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getTime() {
        return eventTime;
    }

}