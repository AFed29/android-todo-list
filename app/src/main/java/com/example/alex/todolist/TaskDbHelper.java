package com.example.alex.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by Alex on 23/03/2018.
 */

public class TaskDbHelper extends DBHelper {

    public TaskDbHelper(Context context) {
        super(context);
    }

    public void save(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ToDoListContract.COLUMN_NAME_TASKNAME, task.getTaskName());
        values.put(ToDoListContract.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(ToDoListContract.COLUMN_NAME_COMPLETED, 0);

        db.insert(ToDoListContract.TABLE_NAME, null, values);
    }

    public ArrayList<Task> selectAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder = ToDoListContract._ID + " DESC";
        Cursor cursor = db.query(ToDoListContract.TABLE_NAME, null, null, null, null, null, sortOrder);
        ArrayList<Task> tasks = new ArrayList<>();

        while (cursor.moveToNext()) {
            String taskName = cursor.getString(
                    cursor.getColumnIndexOrThrow(ToDoListContract.COLUMN_NAME_TASKNAME));
            String taskDescription = cursor.getString(
                    cursor.getColumnIndexOrThrow(ToDoListContract.COLUMN_NAME_DESCRIPTION));
            int completed = cursor.getInt(
                    cursor.getColumnIndexOrThrow(ToDoListContract.COLUMN_NAME_COMPLETED));
            Task task = new Task(taskName, taskDescription, completed);
            tasks.add(task);
        }
        return tasks;
    }
}
