package com.example.alex.todolist.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by Alex on 27/03/2018.
 */

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener onDateSetListener;


    public DatePickerFragment(DatePickerDialog.OnDateSetListener dateSetListener) {
        this.onDateSetListener = dateSetListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), 0, onDateSetListener, year, month, day);
        return datePickerDialog;
    }
}
