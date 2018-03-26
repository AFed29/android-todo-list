package com.example.alex.todolist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.alex.todolist.Models.Task;
import com.example.alex.todolist.R;

import java.util.ArrayList;

/**
 * Created by Alex on 25/03/2018.
 */

public class TasksAdapter extends ArrayAdapter<Task> {

    public TasksAdapter(Context context, ArrayList<Task> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        Task currentTask = getItem(position);

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        TextView taskName = listItemView.findViewById(R.id.taskName);
        ToggleButton completedToggle = listItemView.findViewById(R.id.completed_toggle);

        taskName.setText(currentTask.getTaskName());
        completedToggle.setChecked(currentTask.getCompleted());

        listItemView.setTag(currentTask);
        completedToggle.setTag(currentTask);

        return listItemView;
    }

}


