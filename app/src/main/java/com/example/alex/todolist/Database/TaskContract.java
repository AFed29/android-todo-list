package com.example.alex.todolist.Database;

import android.provider.BaseColumns;

/**
 * Created by Alex on 25/03/2018.
 */

public final class TaskContract implements BaseColumns  {
    private TaskContract() {}

    static final String TABLE_NAME = "tasks";
    static final String COLUMN_NAME_TASKNAME = "task_name";
    static final String COLUMN_NAME_DESCRIPTION = "description";
    static final String COLUMN_NAME_COMPLETED = "completed";
    static final String COLUMN_NAME_PINNED = "pinned";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TaskContract.TABLE_NAME + " (" +
                    TaskContract._ID + " INTEGER PRIMARY KEY," +
                    TaskContract.COLUMN_NAME_TASKNAME + " TEXT," +
                    TaskContract.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    TaskContract.COLUMN_NAME_COMPLETED + " INTEGER," +
                    TaskContract.COLUMN_NAME_PINNED + " INTEGER)";


    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskContract.TABLE_NAME;

}