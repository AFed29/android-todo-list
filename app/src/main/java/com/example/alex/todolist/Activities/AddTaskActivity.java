package com.example.alex.todolist.Activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.alex.todolist.Notifications.NotificationPublisher;
import com.example.alex.todolist.Notifications.TaskNotification;
import com.example.alex.todolist.R;
import com.example.alex.todolist.Models.Task;
import com.example.alex.todolist.Database.TaskDbHelper;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private EditText task_name;
    private EditText description;
    private TextView date;
    private Calendar reminderDateTime;
    private Calendar temporaryCalendar;
    private SimpleDateFormat simpleDateFormat;
    private DialogFragment datePicker;
    private DialogFragment timePicker;

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
        datePicker = new DatePickerFragment(this, Calendar.getInstance());
        datePicker.show(this.getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        temporaryCalendar = Calendar.getInstance();
        temporaryCalendar.clear();
        temporaryCalendar.set(Calendar.YEAR, year);
        temporaryCalendar.set(Calendar.MONTH, month);
        temporaryCalendar.set(Calendar.DAY_OF_MONTH, day);
        timePicker = new TimePickerFragment(this, Calendar.getInstance());
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
            long id = taskDbHelper.save(task);

            task.setId((int) id);

            if (reminderDateTime != null) {
                Notification notification = TaskNotification.notification(this, task);

                Intent notificationIntent = new Intent(this, NotificationPublisher.class);
                notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, task.getId());
                notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, task.getId(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


                AlarmManager alarmManager = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));

                alarmManager.set(AlarmManager.RTC_WAKEUP, task.getReminderDateTime(), pendingIntent);
            }
            Toast.makeText(this, task.getTaskName() + " added", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void onDateTimeDeleteClicked(View view) {
        reminderDateTime = null;
        date.setText(null);
    }
}
