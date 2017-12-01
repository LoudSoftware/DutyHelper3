package com.example.mohammedabu.dutyhelper.dbHelpers;

public class TaskModel {

    private String title;
    private String description;
    private String assignee;
    private String dueDate;
    private String time;
    private String points;
    private String uid;
    private String status;

    public TaskModel() {}  // Needed for Firebase

    public TaskModel(String title, String description, String assignee, String dueDate, String time, String points, String status, String uid) {
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.dueDate = dueDate;
        this.time = time;
        this.points = points;
        this.status = status;
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getUid() {
        return uid;
    }

    public String getAssignee() {return assignee;}

    public String getTime() {
        return time;
    }

    public String getPoints() {
        return points;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}