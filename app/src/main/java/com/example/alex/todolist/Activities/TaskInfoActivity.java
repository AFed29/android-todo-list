package com.example.alex.todolist.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.todolist.Database.TaskDbHelper;
import com.example.alex.todolist.R;
import com.example.alex.todolist.Models.Task;
import com.example.alex.todolist.Utilities.ByteConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TaskInfoActivity extends AppCompatActivity {
    private EditText task_name, task_description;
    private TextView reminderDateTime;
    Task task;
    boolean editing;
    Button updateButton;
    TaskDbHelper taskDbHelper;
    SimpleDateFormat simpleDateFormat;
    Calendar temporary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        simpleDateFormat = new SimpleDateFormat("HH:mm, dd/MM/yyyy");
        temporary = Calendar.getInstance();

        Intent intent = getIntent();
        if (intent.getByteArrayExtra("byte") != null) {
            try {
                task = (Task) ByteConverter.deserialize(intent.getByteArrayExtra("byte"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            task = (Task) intent.getSerializableExtra("task");
        }



        taskDbHelper = new TaskDbHelper(this);

        task_name = findViewById(R.id.task_name_info);
        task_description = findViewById(R.id.task_description_info);
        reminderDateTime = findViewById(R.id.reminder_info_view);
        updateButton = findViewById(R.id.update_button);

         task_name.setText(task.getTaskName());
        task_description.setText(task.getDescription());
        if (task.getReminderDateTime() != null) {
            Long utcNumber = task.getReminderDateTime();
            temporary.setTimeInMillis(utcNumber);
            reminderDateTime.setText(simpleDateFormat.format(temporary.getTime()));
        } else {
            reminderDateTime.setText(null);
        }

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

            Toast.makeText(this, task.getTaskName() + " updated", Toast.LENGTH_LONG).show();
            finish();
        } else {
            editing = true;
            updateButton.setText(R.string.update_task_button_text);
            task_name.setEnabled(editing);
            task_description.setEnabled(editing);
        }
    }
}
