package com.example.alex.todolist;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        TaskDbHelper taskDbHelper = new TaskDbHelper(this);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks = taskDbHelper.selectAll();

        TasksAdapter tasksAdapter = new TasksAdapter(this, tasks);

        ListView listView = findViewById(R.id.task_list);
        listView.setAdapter(tasksAdapter);
        }

    }

