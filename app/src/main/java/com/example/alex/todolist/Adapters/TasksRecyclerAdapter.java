package com.example.alex.todolist.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.alex.todolist.Models.Task;
import com.example.alex.todolist.R;

import java.util.ArrayList;

/**
 * Created by Alex on 26/03/2018.
 */

public class TasksRecyclerAdapter extends RecyclerView.Adapter<TasksRecyclerAdapter.TasksViewHolder> {
    private ArrayList<Task> tasks;



    public class TasksViewHolder extends RecyclerView.ViewHolder {
        public TextView taskName;
        public ToggleButton completedToggle;

        public TasksViewHolder(View view) {
            super(view);
            taskName = view.findViewById(R.id.taskName);
            completedToggle = view.findViewById(R.id.completed_toggle);
        }
    }

    public TasksRecyclerAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TasksViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.taskName.setText(currentTask.getTaskName());
        holder.itemView.setTag(currentTask);
        holder.completedToggle.setChecked(currentTask.getCompleted());
        holder.completedToggle.setTag(currentTask);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }



}
