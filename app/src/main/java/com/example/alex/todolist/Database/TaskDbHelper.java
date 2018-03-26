package com.example.alex.todolist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alex.todolist.Models.Task;

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

        values.put(TaskContract.COLUMN_NAME_TASKNAME, task.getTaskName());
        values.put(TaskContract.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(TaskContract.COLUMN_NAME_COMPLETED, 0);

        db.insert(TaskContract.TABLE_NAME, null, values);
    }

    public ArrayList<Task> selectAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder = TaskContract._ID + " DESC";
        Cursor cursor = db.query(TaskContract.TABLE_NAME, null, null, null, null, null, sortOrder);
        ArrayList<Task> tasks = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(TaskContract._ID));
            String taskName = cursor.getString(
                    cursor.getColumnIndexOrThrow(TaskContract.COLUMN_NAME_TASKNAME));
            String taskDescription = cursor.getString(
                    cursor.getColumnIndexOrThrow(TaskContract.COLUMN_NAME_DESCRIPTION));
            int completed = cursor.getInt(
                    cursor.getColumnIndexOrThrow(TaskContract.COLUMN_NAME_COMPLETED));
            Task task = new Task(id, taskName, taskDescription, completed);
            tasks.add(task);
        }
        return tasks;
    }

    public void updateCompleted(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.COLUMN_NAME_COMPLETED, task.getCompleted());

        String selection = TaskContract._ID + " LIKE ?";
        String[] selectionArgs = { task.getId().toString() };

        db.update(TaskContract.TABLE_NAME, values, selection, selectionArgs);
    }
}
