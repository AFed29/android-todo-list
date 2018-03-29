package com.example.alex.todolist.Notifications;

import android.app.Notification;
import android.content.Context;
import android.os.Build;

import com.example.alex.todolist.R;

/**
 * Created by Alex on 29/03/2018.
 */

public class SummaryNotification {

    public static Notification summaryNotification(Context context) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("Tasks");
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setGroup("Tasks");
            builder.setGroupSummary(true);
            return builder.build();
        }
        return null;
    }
}
