package com.example.alex.todolist.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alex.todolist.Fragments.DatePickerFragment;
import com.example.alex.todolist.Fragments.TimePickerFragment;
import com.example.alex.todolist.R;
import com.example.alex.todolist.Models.Task;
import com.example.alex.todolist.Database.TaskDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    EditText task_name;
    EditText description;
    TextView date;
    Calendar reminderDateTime;
    Calendar temporaryCalendar;
    SimpleDateFormat simpleDateFormat;
    DialogFragment datePicker;
    DialogFragment timePicker;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        task_name = findViewById(R.id.task_name_input);
        description = findViewById(R.id.task_description_input);
        date = findViewById(R.id.date_add);

        reminderDateTime = null;
        simpleDateFormat = new SimpleDateFormat("HH:mm, dd/MM/yyyy");
    }

    public void onDateClicked(View view) {
        datePicker = new DatePickerFragment(this);
        datePicker.show(this.getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        temporaryCalendar = Calendar.getInstance();
        temporaryCalendar.clear();
        temporaryCalendar.set(Calendar.YEAR, year);
        temporaryCalendar.set(Calendar.MONTH, month);
        temporaryCalendar.set(Calendar.DAY_OF_MONTH, day);
        timePicker = new TimePickerFragment(this);
        timePicker.show(this.getFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        reminderDateTime = temporaryCalendar;
        reminderDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        reminderDateTime.set(Calendar.MINUTE, minute);
        date.setText(simpleDateFormat.format(reminderDateTime.getTime()));
    }

    public void onAddTaskButtonClicked(View view) {
        String task_name_to_save = task_name.getText().toString();
        if (task_name_to_save.trim().equals("") || task_name_to_save.isEmpty()) {
            Toast.makeText(this, "Please Enter a task name", Toast.LENGTH_LONG).show();
        } else {
            String description_to_save = description.getText().toString();

            Task task = new Task(task_name_to_save, description_to_save, reminderDateTime);

            TaskDbHelper taskDbHelper = new TaskDbHelper(this);
            taskDbHelper.save(task);
            finish();
        }
    }
}
