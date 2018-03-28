package com.example.alex.todolist.Models;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Alex on 25/03/2018.
 */

public class Task implements Serializable {
    private int id;
    private String taskName;
    private String description;
    private boolean completed;
    private boolean pinned;
    private Calendar reminderDateTime = Calendar.getInstance();

    public Task(int id, String taskName, String description, int completed, int pinned, Calendar reminderDateTime) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.completed = completed == 1;
        this.pinned = pinned == 1;
        this.reminderDateTime = reminderDateTime;
    }

    public Task(String taskName, String description, Calendar reminderDateTime) {
        if (taskName.trim().equals("") || taskName.isEmpty()) {
            this.taskName = null;
        } else {
            this.taskName = taskName;
        }
        this.description = description;
        this.completed = false;
        this.pinned = false;
        if (reminderDateTime == null) {
            this.reminderDateTime = null;
        } else {
            this.reminderDateTime = reminderDateTime;
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public Integer getId() {
        return id;
    }

    public void flipCompleted() {
        this.completed = !this.completed;
    }

    public void setName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getPinned() {
        return pinned;
    }

    public void flipPinned() {
        this.pinned = !this.pinned;
    }

    public Long getReminderDateTime() {
        if (reminderDateTime != null) {
            return reminderDateTime.getTimeInMillis();
        } else {
            return null;
        }
    }
}
