package com.example.alex.todolist;

import android.provider.BaseColumns;

/**
 * Created by Alex on 25/03/2018.
 */

public final class ToDoListContract implements BaseColumns  {
    private ToDoListContract() {}

    static final String TABLE_NAME = "tasks";
    static final String COLUMN_NAME_TASKNAME = "task_name";
    static final String COLUMN_NAME_DESCRIPTION = "description";
    static final String COLUMN_NAME_COMPLETED = "completed";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ToDoListContract.TABLE_NAME + " (" +
                    ToDoListContract._ID + " INTEGER PRIMARY KEY," +
                    ToDoListContract.COLUMN_NAME_TASKNAME + " TEXT," +
                    ToDoListContract.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    ToDoListContract.COLUMN_NAME_COMPLETED + " INTEGER)";


    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ToDoListContract.TABLE_NAME;

}