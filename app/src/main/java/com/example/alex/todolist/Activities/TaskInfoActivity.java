package com.example.alex.todolist.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.alex.todolist.Database.TaskDbHelper;
import com.example.alex.todolist.R;
import com.example.alex.todolist.Models.Task;

public class TaskInfoActivity extends AppCompatActivity {
    private TextView task_name;
    private TextView task_description;
    Task task;
    boolean editing;
    Button updateButton;
    TaskDbHelper taskDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("task");

        taskDbHelper = new TaskDbHelper(this);

        task_name = findViewById(R.id.task_name_info);
        task_description = findViewById(R.id.task_description_info);
        updateButton = findViewById(R.id.update_button);

        task_name.setText(task.getTaskName());
        task_description.setText(task.getDescription());

        editing = false;
        task_name.setEnabled(editing);
        task_description.setEnabled(editing);
    }

    public void onDeleteButtonClicked(View view) {
        taskDbHelper.deleteOne(task);
        finish();
    }

    public void onEditClicked(View view) {
        if (editing) {
            String edited_name = task_name.getText().toString();
            String edited_description = task_description.getText().toString();

            task.setName(edited_name);
            task.setDescription(edited_description);

            taskDbHelper.update(task);
            finish();
        } else {
            editing = true;
            updateButton.setText(R.string.update_task_button_text);
            task_name.setEnabled(editing);
            task_description.setEnabled(editing);
        }
    }
}
