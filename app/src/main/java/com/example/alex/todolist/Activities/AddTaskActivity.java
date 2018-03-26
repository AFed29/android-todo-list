package com.example.alex.todolist.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.alex.todolist.R;
import com.example.alex.todolist.Models.Task;
import com.example.alex.todolist.Database.TaskDbHelper;

public class AddTaskActivity extends AppCompatActivity {
    EditText task_name;
    EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        task_name = findViewById(R.id.task_name_input);
        description = findViewById(R.id.task_description_input);
    }

    public void onAddTaskButtonClick(View view) {
        String task_name_to_save = task_name.getText().toString();
        String description_to_save = description.getText().toString();
        Task task = new Task(task_name_to_save, description_to_save);

        TaskDbHelper taskDbHelper = new TaskDbHelper(this);
        taskDbHelper.save(task);
        finish();
    }
}
