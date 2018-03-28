package com.example.alex.todolist.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import java.util.Calendar;

/**
 * Created by Alex on 27/03/2018.
 */

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener onTimeSetListener;

    public TimePickerFragment(TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        this.onTimeSetListener = onTimeSetListener;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this.onTimeSetListener, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
