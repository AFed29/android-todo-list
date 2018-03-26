package com.example.alex.todolist;

import java.io.Serializable;

/**
 * Created by Alex on 25/03/2018.
 */

public class Task implements Serializable {
    private int id;
    private String taskName;
    private String description;
    private boolean completed;

    public Task(int id, String taskName, String description, int completed) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.completed = completed == 1;
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
