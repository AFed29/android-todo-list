package com.example.alex.todolist.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import java.util.Calendar;

/**
 * Created by Alex on 27/03/2018.
 */

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener onTimeSetListener;
    private Calendar calendar;

    public TimePickerFragment(TimePickerDialog.OnTimeSetListener onTimeSetListener, Calendar calendar) {
        this.onTimeSetListener = onTimeSetListener;
        this.calendar = calendar;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(
                getActivity(), this.onTimeSetListener, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
