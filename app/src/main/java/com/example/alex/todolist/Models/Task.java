package com.example.alex.todolist.Models;

import java.io.Serializable;

/**
 * Created by Alex on 25/03/2018.
 */

public class Task implements Serializable {
    private int id;
    private String taskName;
    private String description;
    private boolean completed;
    private boolean pinned;

    public Task(int id, String taskName, String description, int completed, int pinned) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.completed = completed == 1;
        this.pinned = pinned == 1;
    }

    public Task(String taskName, String description) {
        if (taskName.trim().equals("") || taskName.isEmpty()) {
            this.taskName = null;
        } else {
            this.taskName = taskName;
        }
        this.description = description;
        this.completed = false;
        this.pinned = false;
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
}
