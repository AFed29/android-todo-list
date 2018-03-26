package com.example.alex.todolist.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.alex.todolist.Database.TaskDbHelper;
import com.example.alex.todolist.R;
import com.example.alex.todolist.Models.Task;

public class TaskInfoActivity extends AppCompatActivity {
    private TextView task_name;
    private TextView task_description;
    Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("task");

        task_name = findViewById(R.id.task_name_info);
        task_description = findViewById(R.id.task_description_info);

        task_name.setText(task.getTaskName());
        task_description.setText(task.getDescription());
    }

    public void onDeleteButtonClicked(View view) {
        TaskDbHelper taskDbHelper = new TaskDbHelper(this);
        taskDbHelper.deleteOne(task);
        finish();
    }
}
