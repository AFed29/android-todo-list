package com.example.alex.todolist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alex.todolist.Models.Task;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Alex on 23/03/2018.
 */

public class TaskDbHelper extends DBHelper {

    public TaskDbHelper(Context context) {
        super(context);
    }

    public long save(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TaskContract.COLUMN_NAME_TASKNAME, task.getTaskName());
        values.put(TaskContract.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(TaskContract.COLUMN_NAME_COMPLETED, task.getCompleted());
        values.put(TaskContract.COLUMN_NAME_PINNED, task.getPinned());
        values.put(TaskContract.COLUMN_NAME_REMINDER_DATE_TIME, task.getReminderDateTime());

        return db.insert(TaskContract.TABLE_NAME, null, values);
    }

    public ArrayList<Task> selectAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sortOrder = TaskContract.COLUMN_NAME_PINNED + " DESC, " + TaskContract._ID + " DESC";
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
            int pinned = cursor.getInt(
                    cursor.getColumnIndexOrThrow(TaskContract.COLUMN_NAME_PINNED));
            long reminderDateTime = cursor.getLong(
                    cursor.getColumnIndexOrThrow(TaskContract.COLUMN_NAME_REMINDER_DATE_TIME));

            Calendar calendar = null;
            if (reminderDateTime != 0) {
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(reminderDateTime);
            }
            Task task = new Task(id, taskName, taskDescription, completed, pinned, calendar);
            tasks.add(task);
        }
        return tasks;
    }

    public void update(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.COLUMN_NAME_TASKNAME, task.getTaskName());
        values.put(TaskContract.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(TaskContract.COLUMN_NAME_COMPLETED, task.getCompleted());
        values.put(TaskContract.COLUMN_NAME_PINNED, task.getPinned());
        values.put(TaskContract.COLUMN_NAME_REMINDER_DATE_TIME, task.getReminderDateTime());

        String selection = TaskContract._ID + " = ?";
        String[] selectionArgs = { task.getId().toString() };

        db.update(TaskContract.TABLE_NAME, values, selection, selectionArgs);
    }

    public void deleteOne(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = TaskContract._ID + " = ?";
        String[] selectionArgs = { task.getId().toString() };

        db.delete(TaskContract.TABLE_NAME, selection, selectionArgs);
    }
}
