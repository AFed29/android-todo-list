package com.example.alex.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        TaskDbHelper taskDbHelper = new TaskDbHelper(this);
        ArrayList<Task> tasks;
        tasks = taskDbHelper.selectAll();

        TasksAdapter tasksAdapter = new TasksAdapter(this, tasks);

        ListView listView = findViewById(R.id.task_list);
        listView.setAdapter(tasksAdapter);
        }

    protected void onResume() {
        super.onResume();

        TaskDbHelper taskDbHelper = new TaskDbHelper(this);
        ArrayList<Task> tasks;
        tasks = taskDbHelper.selectAll();

        TasksAdapter tasksAdapter = new TasksAdapter(this, tasks);

        ListView listView = findViewById(R.id.task_list);
        listView.setAdapter(tasksAdapter);
    }


    public void onAddTaskButtonClicked(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

}

