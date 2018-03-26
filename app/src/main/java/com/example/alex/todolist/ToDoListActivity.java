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

    TaskDbHelper taskDbHelper;
    ArrayList<Task> tasks;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        listView = findViewById(R.id.task_list);
        taskDbHelper = new TaskDbHelper(this);
        }

    @Override
    protected void onResume() {
        super.onResume();

        tasks = taskDbHelper.selectAll();

        TasksAdapter tasksAdapter = new TasksAdapter(this, tasks);
        listView.setAdapter(tasksAdapter);
    }


    public void onAddTaskButtonClicked(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void onTaskClicked(View listItem) {
        Intent intent = new Intent(this, TaskInfoActivity.class);
        Task task = (Task) listItem.getTag();
        intent.putExtra("task", task);
        startActivity(intent);
    }

}

