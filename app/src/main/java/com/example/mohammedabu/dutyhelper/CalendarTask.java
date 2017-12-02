package com.example.mohammedabu.dutyhelper;

/**
 * Deprecated Task Model representation. Use the TaskModel class instead
 */


@Deprecated
public class CalendarTask {
        private String taskID;
        private String eventName;
        private String eventDate;
        private String eventTime;
        private String eventDescription;
        private String assignee;
        private int eventPoints;

        public CalendarTask(String eventName, String eventDate, String eventTime, String eventDescription, int eventPoints, String assignee) {
            this.eventName=eventName;
            this.eventDate=eventDate;
            this.eventTime=eventTime;
            this.eventDescription=eventDescription;
            this.eventPoints=eventPoints;
            this.assignee=assignee;
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
        public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }
        public String getEventDescription() {
            return eventDescription;
        }
        public void setPoints(int eventPoints) {
            this.eventPoints=eventPoints;
        }
        public double getPoints() {
            return eventPoints;
        }
        public void setAssignee(String assignee) { this.assignee = assignee; }
        public String getAssignee() {
        return assignee;
    }
        public void setTime(String eventTime) { this.eventTime = eventTime; }
        public String getTime() {
        return eventTime;
    }

}