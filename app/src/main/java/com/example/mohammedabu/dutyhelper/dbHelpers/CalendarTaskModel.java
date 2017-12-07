package com.example.mohammedabu.dutyhelper.dbHelpers;

/**
 * Deprecated due to it being the same as TaskModel
 */
@Deprecated
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

    public String getId() {
        return taskID;
    }

    public void setId(String taskID) {
        this.taskID = taskID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getTime() {
        return eventTime;
    }

    public void setTime(String eventTime) {
        this.eventTime = eventTime;
    }

}