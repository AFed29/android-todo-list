package com.example.alex.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaskInfoActivity extends AppCompatActivity {
    private TextView task_name;
    private TextView task_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        Intent intent = getIntent();
        Task task = (Task) intent.getSerializableExtra("task");

        task_name = findViewById(R.id.task_name_info);
        task_description = findViewById(R.id.task_description_info);

        task_name.setText(task.getTaskName());
        task_description.setText(task.getDescription());
    }
}
