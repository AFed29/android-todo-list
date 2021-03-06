package com.example.alex.todolist.Activities;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alex.todolist.Database.TaskDbHelper;
import com.example.alex.todolist.Fragments.DatePickerFragment;
import com.example.alex.todolist.Fragments.TimePickerFragment;
import com.example.alex.todolist.Notifications.TaskReminderCreator;
import com.example.alex.todolist.R;
import com.example.alex.todolist.Models.Task;
import com.example.alex.todolist.Utilities.ByteConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TaskInfoActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private EditText taskName, taskDescription;
    private TextView reminderDateTimeTextView, editReminderTextView;
    private Task task;
    boolean editing;
    private Button updateButton;
    private TaskDbHelper taskDbHelper;
    private SimpleDateFormat simpleDateFormat;
    private Calendar displayTemp;
    private Calendar temporaryCalendar;
    private Calendar reminderDateTime;
    private Calendar pickerCalendar;
    private DialogFragment datePicker;
    private DialogFragment timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        simpleDateFormat = new SimpleDateFormat("HH:mm, dd/MM/yyyy");

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

        taskName = findViewById(R.id.task_name_info);
        taskDescription = findViewById(R.id.task_description_info);
        reminderDateTimeTextView = findViewById(R.id.reminder_info_view);
        updateButton = findViewById(R.id.update_button);
        editReminderTextView = findViewById(R.id.edit_reminder_text_view);

        taskName.setText(task.getTaskName());
        taskDescription.setText(task.getDescription());
        if (task.getReminderDateTime() != null) {
            Long utcNumber = task.getReminderDateTime();
            displayTemp = Calendar.getInstance();
            displayTemp.setTimeInMillis(utcNumber);
            reminderDateTimeTextView.setText(simpleDateFormat.format(displayTemp.getTime()));
        } else {
            reminderDateTimeTextView.setText(null);
            editReminderTextView.setVisibility(View.INVISIBLE);
        }

        editing = false;
        taskName.setEnabled(editing);
        taskDescription.setEnabled(editing);
    }

    public void onDeleteButtonClicked(View view) {
        taskDbHelper.deleteOne(task);
        finish();
    }

    public void onDateClicked(View view) {
        if (displayTemp != null) {
            pickerCalendar = displayTemp;
        } else {
            pickerCalendar = Calendar.getInstance();
        }
        datePicker = new DatePickerFragment(this, pickerCalendar);
        datePicker.show(this.getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        temporaryCalendar = Calendar.getInstance();
        temporaryCalendar.clear();
        temporaryCalendar.set(Calendar.YEAR, year);
        temporaryCalendar.set(Calendar.MONTH, month);
        temporaryCalendar.set(Calendar.DAY_OF_MONTH, day);
        timePicker = new TimePickerFragment(this, pickerCalendar);
        timePicker.show(this.getFragmentManager(), "timePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        reminderDateTime = temporaryCalendar;
        reminderDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        reminderDateTime.set(Calendar.MINUTE, minute);
        reminderDateTimeTextView.setText(simpleDateFormat.format(reminderDateTime.getTime()));
    }

    public void onEditClicked(View view) {
        if (editing) {
            String edited_name = taskName.getText().toString();
            String edited_description = taskDescription.getText().toString();

            if (edited_name.trim().equals("") || edited_name.isEmpty()) {
                Toast.makeText(this, "Please Enter a task name", Toast.LENGTH_LONG).show();
            } else {

                task.setName(edited_name);
                task.setDescription(edited_description);
                task.setReminderDateTime(reminderDateTime);

                taskDbHelper.update(task);

                if (reminderDateTime != null) {
                    TaskReminderCreator.createReminder(this, task);
                    Toast.makeText(this, task.getTaskName() + " updated. Reminder at \n" +
                            reminderDateTimeTextView.getText().toString(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, task.getTaskName() +
                            " updated", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        } else {
            editing = true;
            updateButton.setText(R.string.update_task_button_text);
            taskName.setEnabled(editing);
            taskDescription.setEnabled(editing);
        }
    }
}
