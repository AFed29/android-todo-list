package com.example.alex.todolist.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by Alex on 27/03/2018.
 */

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Calendar calendar;

    public DatePickerFragment(DatePickerDialog.OnDateSetListener dateSetListener, Calendar calendar) {
        this.onDateSetListener = dateSetListener;
        this.calendar = calendar;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), 0, onDateSetListener, year, month, day);
    }
}
