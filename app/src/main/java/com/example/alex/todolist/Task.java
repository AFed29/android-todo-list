package com.example.alex.todolist;

/**
 * Created by Alex on 25/03/2018.
 */

public class Task {
    private String taskName;
    private String description;
    private boolean completed;

    public Task(String taskName, String description, int completed) {
        this.taskName = taskName;
        this.description = description;
        if (completed == 0) {
            this.completed = false;
        } else {
            this.completed = true;
        }
    }

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.completed = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

}
