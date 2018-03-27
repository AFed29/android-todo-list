package com.example.alex.todolist.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alex.todolist.Adapters.TasksRecyclerAdapter;
import com.example.alex.todolist.R;
import com.example.alex.todolist.Models.Task;
import com.example.alex.todolist.Database.TaskDbHelper;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity {

    TaskDbHelper taskDbHelper;
    ArrayList<Task> tasks;
    RecyclerView recyclerView;
    TasksRecyclerAdapter tasksRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        recyclerView = findViewById(R.id.tasks_list);
        taskDbHelper = new TaskDbHelper(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshUI();
    }


    public void refreshUI() {
        tasks = taskDbHelper.selectAll();

        tasksRecyclerAdapter = new TasksRecyclerAdapter(tasks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tasksRecyclerAdapter);
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

    public void onCompletedChanged(View view) {
        Task task = (Task) view.getTag();
        task.flipCompleted();
        taskDbHelper.update(task);
    }

    public void onPinnedChanged(View view) {
        Task task = (Task) view.getTag();
        task.flipPinned();
        taskDbHelper.update(task);
        refreshUI();
    }
}

